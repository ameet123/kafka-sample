package org.ameet.kafkasample.service;

import org.ameet.kafkasample.Util;
import org.ameet.kafkasample.config.AppConfig;
import org.apache.kafka.common.metrics.KafkaMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameet.chaubal on 6/12/2017.
 * service layer processor to add items to kafka
 */
@Service
public class KafkaProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessor.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private AppConfig appConfig;

    public String getSimpleTopic() {
        return simpleTopic;
    }

    @Value(value = "${topic.simple.name}")
    private String simpleTopic;

    private AtomicInteger sentCount = new AtomicInteger(0);

    @Autowired
    public KafkaProcessor(KafkaTemplate<String, String> kafkaTemplate, AppConfig appConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.appConfig = appConfig;
    }

    /**
     * add message to kafka queue
     *
     * @return CompletableFuture
     */
    @Async
    public CompletableFuture<Integer> submit(String message) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Topic:[{}] Sending async msg->{}", simpleTopic, message);
            kafkaTemplate.send(simpleTopic, message);
            totalCount();
            Util.delay(5000L);
            return sentCount.incrementAndGet();
        }, appConfig.getAsyncExecutor());
    }

    @Async
    public void submitAsync(String message) {
        LOGGER.debug("Topic:[{}] Sending simple async msg->{} cnt:{}", simpleTopic, message, sentCount
                .incrementAndGet());
        kafkaTemplate.send(simpleTopic, message);
    }

    private void totalCount() {
        KafkaMetric countMetric = (KafkaMetric) kafkaTemplate.metrics().entrySet().stream().
                filter(metricNameEntry -> metricNameEntry.getKey().name().equals("count")).
                map(Map.Entry::getValue).
                findFirst().orElse(null);
        LOGGER.debug("Total Count==>{}", countMetric.value());
    }

    /**
     * get a map of metrics from kafka
     *
     * @return
     */
    public Map<String, Double> metrics() {
        Map<String, Double> metricMap = new HashMap<>();
        kafkaTemplate.metrics().forEach((metricName, o) -> metricMap.put(metricName.name(), o.value()));
        return metricMap;
    }
}
