package org.ameet.kafkasample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Random;

/**
 * Created by Ameet Chaubal on 5/18/2017.
 */
@Component
public class PublishTester {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishTester.class);
    private static final String SAMPLE_MSG = "hello ameet!";
    private Random random = new Random();

    @Value(value = "${topic.simple.name}")
    private String simpleTopic;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public ListenableFuture<SendResult<String, String>> publishSimple() {
        LOGGER.info("Sending msg to topic:{}", simpleTopic);
        return kafkaTemplate.send(simpleTopic, SAMPLE_MSG + "--" + random.nextInt());
    }
}