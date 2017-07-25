package org.ameet.kafkasample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ameet.kafkasample.dao.MetadataDAO;
import org.ameet.kafkasample.model.KafkaMessage;
import org.ameet.kafkasample.model.MessageMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ameet.chaubal on 7/14/2017.
 * generic way to process message on separate threads and in a buffer
 * so we can do bulk processing on db inserts
 * the observable stream thus created gives us an automatic collection of events or messages.
 * the events or messages are collected based on a max count and a time window threshold.
 * based on this we can perform batching as we wish.
 */
@Component
public class MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);
    private SerializedSubject<String, String> safeSource;
    private SerializedSubject<MessageMetadata, MessageMetadata> safeKafkaMessageSource;
    private ObjectMapper mapper;
    private MetadataDAO metadataDAO;

    /**
     * we set up the stream here on constructor, which is a Subscriber as an Oservable
     * and then publish to it using a method. Since this is bufferred, the subscriber receives
     * a List.
     *
     * @param taskExecutor
     */
    @Autowired
    public MessageProcessor(ThreadPoolTaskExecutor taskExecutor, ObjectMapper mapper, MetadataDAO metadataDAO,
                            @Value("${app.observable.buffer.size}") int bufferSize,
                            @Value("${app.observable.buffer.window.span.sec}") int bufferSpanSeconds) {
        LOGGER.debug("buffer size:{} windowTimeSec:{}", bufferSize, bufferSpanSeconds);
        this.metadataDAO = metadataDAO;
        this.mapper = mapper;
        safeSource = PublishSubject.<String>create().toSerialized();
        safeSource
                .subscribeOn(Schedulers.newThread())
                .buffer(bufferSpanSeconds, TimeUnit.SECONDS, bufferSize)
                .onBackpressureBuffer(bufferSize * 2)
                .observeOn(Schedulers.from(taskExecutor))
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        LOGGER.debug("Batch completed..");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LOGGER.error("ERR: in batch processing", e);
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        if (strings == null || strings.size() == 0) {
                            return;
                        }
                        LOGGER.debug(">>>>> Arrived on Bus on:{}", Thread.currentThread().getName());
                        Observable.just(strings).observeOn(Schedulers.newThread())
                                .subscribe(strings1 -> LOGGER.debug(">> Batch size={}", strings1.size()));
                    }
                });
        // create subject for kafka message with similar characteristics as above
        safeKafkaMessageSource = PublishSubject.<MessageMetadata>create().toSerialized();
        safeKafkaMessageSource
                .buffer(bufferSpanSeconds, TimeUnit.SECONDS, bufferSize)
                .onBackpressureBuffer(bufferSize * 2)
                .subscribe(new Subscriber<List<MessageMetadata>>() {
                    @Override
                    public void onCompleted() {
                        LOGGER.debug("Batch completed..");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LOGGER.error("ERR: in batch processing", e);
                    }

                    @Override
                    public void onNext(List<MessageMetadata> kafkaMessageList) {
                        if (kafkaMessageList == null || kafkaMessageList.size() == 0) {
                            LOGGER.trace("## Null/Zero Arrived on Bus on:{}", Thread.currentThread().getName());
                            return;
                        }
                        LOGGER.debug(">>>>> Arrived on Bus on:{}", Thread.currentThread().getName());
                        Observable.just(kafkaMessageList).observeOn(Schedulers.newThread())
                                .subscribe(strings1 -> {
                                    LOGGER.debug(">> Batch size={}", strings1.size());
                                    metadataDAO.batchInsertByTemplate(kafkaMessageList);
                                });
                    }
                });
    }


    public void publishMetadata(String s) {
        LOGGER.trace("[{}]... publishing now", Thread.currentThread().getName());
        Observable.just(s).
                flatMap(s1 -> Observable.just(s1)
                        .subscribeOn(Schedulers.computation())
                        .map(this::unmarshalMetadata))
                .subscribe(kafkaMessage -> {
                    LOGGER.trace(">>[{}] sent to Bus on->{}",
                            Thread.currentThread().getName(), kafkaMessage.getMessageId());
                    safeKafkaMessageSource.onNext(kafkaMessage);
                });
        LOGGER.trace(">>[{}]->Publishing completed", Thread.currentThread().getName());
    }


    private MessageMetadata unmarshalMetadata(String s) {
        LOGGER.trace(">> Unmarshal on->{}", Thread.currentThread().getName());
        KafkaMessage kafkaMessage = null;
        try {
            kafkaMessage = mapper.readValue(s, KafkaMessage.class);
        } catch (IOException e) {
            LOGGER.error("ERR: unmarshalling metadata json{}:\n{}\n", e, s);
            return null;
        }
        return kafkaMessage.getMessageMetadata();
    }

    private String delayedStringProcessing(String s) {
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted", e);
        }
        return s + "-" + Thread.currentThread().getName();
    }

    public void publish(String s) {
        LOGGER.debug("[{}]... publishing now", Thread.currentThread().getName());
        Observable.just(s).flatMap(s1 -> Observable.just(s1).
                subscribeOn(Schedulers.computation()).
                map(this::delayedStringProcessing)).
                subscribe(s1 -> {
                    LOGGER.info(">> sent to Bus on->{}", Thread.currentThread().getName());
                    safeSource.onNext(s1);
                });

        LOGGER.debug(">>[{}]->Publishing completed", Thread.currentThread().getName());
    }
}