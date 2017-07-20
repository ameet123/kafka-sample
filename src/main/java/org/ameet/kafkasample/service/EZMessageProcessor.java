package org.ameet.kafkasample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ameet.kafkasample.model.ihg.EZMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
@Component
public class EZMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(EZMessageProcessor.class);
    SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor("BUS__");
    private ObjectMapper mapper = new ObjectMapper();
    private SerializedSubject<String, String> safeSource;

    public EZMessageProcessor() {
        simpleAsyncTaskExecutor.setThreadNamePrefix("BUS__");
        safeSource = PublishSubject.<String>create().toSerialized();
        safeSource
                .buffer(3, TimeUnit.SECONDS, 5)
                .observeOn(Schedulers.from(simpleAsyncTaskExecutor))
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        for (String s : strings) {
                            LOGGER.debug(">> received:{}", s);
                        }
                    }
                });
//        safeSource.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                LOGGER.info(">> completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LOGGER.error("ERR");
//            }
//
//            @Override
//            public void onNext(String s) {
//                LOGGER.info(">> received:{}", s);
//            }
//        });
    }

    public EZMessage unmarshalEz(String message) throws IOException {
        return mapper.readValue(message, EZMessage.class);
    }

    public void publish(String s) {
        LOGGER.debug("... publishing now");
        safeSource.onNext(s);
    }
}
