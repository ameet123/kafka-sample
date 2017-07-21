package org.ameet.kafkasample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ameet.kafkasample.model.KafkaMessage;
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
    private SerializedSubject<KafkaMessage, KafkaMessage> safeKafkaMessageSource;
    private ObjectMapper mapper;

    /**
     * we set up the stream here on constructor, which is a Subscriber as an Oservable
     * and then publish to it using a method. Since this is bufferred, the subscriber receives
     * a List.
     *
     * @param taskExecutor
     */
    @Autowired
    public MessageProcessor(ThreadPoolTaskExecutor taskExecutor, ObjectMapper mapper,
                            @Value("${app.observable.buffer.size}") int bufferSize,
                            @Value("${app.observable.buffer.window.span.sec}") int bufferSpanSeconds) {
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
                        Observable.just(strings).observeOn(Schedulers.newThread())
                                .subscribe(strings1 -> LOGGER.debug(">> Batch size={}", strings1.size()));
//                        try {
//                            Thread.sleep(20000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        LOGGER.debug(">>> Batch size:{}", strings.size());
                    }
                });
        // create subject for kafka message with similar characteristics as above
        safeKafkaMessageSource = PublishSubject.<KafkaMessage>create().toSerialized();
        safeKafkaMessageSource
                .buffer(bufferSpanSeconds, TimeUnit.SECONDS, bufferSize)
                .onBackpressureBuffer(bufferSize * 2)
                .observeOn(Schedulers.from(taskExecutor))
                .subscribe(new Subscriber<List<KafkaMessage>>() {
                    @Override
                    public void onCompleted() {
                        LOGGER.debug("Batch completed..");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LOGGER.error("ERR: in batch processing", e);
                    }

                    @Override
                    public void onNext(List<KafkaMessage> strings) {
                        Observable.just(strings).observeOn(Schedulers.newThread())
                                .subscribe(strings1 -> LOGGER.debug(">> Batch size={}", strings1.size()));
                    }
                });
    }

    public void publish(String s) {
//        LOGGER.debug("[{}]... publishing now", Thread.currentThread().getName());
//        safeSource.onNext(s);
        Observable.just(s).
                flatMap(
                        s1 ->
                                Observable.just(s1)
                                        .subscribeOn(Schedulers.computation())
                                        .map(this::unmarshalMetadata))
                .subscribe(kafkaMessage -> safeKafkaMessageSource.onNext(kafkaMessage));
    }

    private KafkaMessage unmarshalMetadata(String s) {
        KafkaMessage kafkaMessage = null;
        try {
            kafkaMessage = mapper.readValue(s, KafkaMessage.class);
        } catch (IOException e) {
            LOGGER.error("ERR: unmarshalling metadata json:\n{}\n", s);
        }
        return kafkaMessage;
    }
}