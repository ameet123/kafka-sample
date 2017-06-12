package org.ameet.kafkasample.controller;

import org.ameet.kafkasample.service.KafkaProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameet.chaubal on 6/12/2017.
 * for kafka message interactions
 */
@RestController
@RequestMapping("/message")
public class MessagingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingController.class);
    private static final String TEMPLATE = "howdy %s!";
    private AtomicInteger index = new AtomicInteger(0);

    private final KafkaProcessor kafkaProcessor;

    @Autowired
    public MessagingController(KafkaProcessor kafkaProcessor) {
        this.kafkaProcessor = kafkaProcessor;
    }

    @RequestMapping("/submit/{content}")
    public CompletableFuture<Integer> submit(@PathVariable String content) {
        LOGGER.debug(">> received message on kafka submit...");
        return kafkaProcessor.submit(content);
    }
}
