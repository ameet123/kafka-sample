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
 * to publish
 */
@Component
public class PublishTester {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishTester.class);
    private static final String SAMPLE_MSG = "hello ameet!";
    private Random random = new Random();

    @Value(value = "${topic.simple.name}")
    private String simpleTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public PublishTester(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Bean
    public ListenableFuture<SendResult<String, String>> publishSimple() {
        String message = SAMPLE_MSG + "--" + random.nextInt();
        LOGGER.info("Topic:[{}] Sending msg->{}", simpleTopic, message);
        return kafkaTemplate.send(simpleTopic, message);
    }
}