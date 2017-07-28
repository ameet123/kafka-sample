package org.ameet.kafkasample.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.ameet.kafkasample.Util;
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
    private static final String CF_NUMBER_FIELD_NAME = "cfNumber";
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
//                            LOGGER.trace("## Null/Zero Arrived on Bus on:{}", Thread.currentThread().getName());
                            return;
                        }
                        LOGGER.trace(">>>>> Arrived on Bus on:{}", Thread.currentThread().getName());
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
        MessageMetadata messageMetadata = null;
        try {
            kafkaMessage = mapper.readValue(s, KafkaMessage.class);
            messageMetadata = kafkaMessage.getMessageMetadata();
            if (Strings.isNullOrEmpty(kafkaMessage.getRawData())){
                LOGGER.error("ERR: rawData is null:{}", s);
            }
            String cfNumber = getCfNumber(kafkaMessage.getRawData());
            if (messageMetadata != null) {
                if (!Strings.isNullOrEmpty(cfNumber)) {
                    messageMetadata.setCfNumber(cfNumber);
                }
                if (messageMetadata.getHotelCodes() != null && messageMetadata.getHotelCodes().size() > 0) {
                    messageMetadata.setHotelCode(messageMetadata.getHotelCodes().toString());
                }
            }
        } catch (IOException e) {
            LOGGER.error("ERR: unmarshalling metadata json{}:\n{}\n", e, s);
            return null;
        }
        return messageMetadata;
    }


    private String getCfNumber(String raw) {
        String rawData = Util.decodeDeflate(raw);
        String cfNumber = null;
        try {
            JsonNode node = mapper.readTree(rawData);
            JsonNode cfNode = Util.recursiveSearch(node, CF_NUMBER_FIELD_NAME);
            if (cfNode != null) {
                cfNumber = cfNode.asText();
            }
        } catch (IOException e) {
//            LOGGER.error("ERR reading json tree from decoded message, probably not json", e);
        }
        return cfNumber;
    }
}