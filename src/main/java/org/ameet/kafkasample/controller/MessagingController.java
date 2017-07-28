package org.ameet.kafkasample.controller;

import com.google.common.base.Stopwatch;
import org.ameet.kafkasample.service.KafkaProcessor;
import org.ameet.kafkasample.service.MessageProcessor;
import org.ameet.kafkasample.service.SampleMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
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
    private final KafkaProcessor kafkaProcessor;
    private SampleMessageService sampleMessageService;
    private MessageProcessor messageProcessor;

    @Autowired
    public MessagingController(KafkaProcessor kafkaProcessor, SampleMessageService sampleMessageService,
                               MessageProcessor messageProcessor) {
        this.kafkaProcessor = kafkaProcessor;
        this.sampleMessageService = sampleMessageService;
        this.messageProcessor = messageProcessor;
    }

    @RequestMapping("/submitEz")
    public String submitEZ() {
        LOGGER.debug(">> EZ sent");
        kafkaProcessor.submitEzSample();
        return "EZ Message sent";
    }

    @RequestMapping("/submitJsonReserv")
    public String submitReservation() {
        LOGGER.debug(">> Reserve JSON sent");
        kafkaProcessor.submitJsonReservationSample();
        return "Reservation JSON Message sent";
    }

    @RequestMapping("/submitXml")
    public String submitXml() {
        LOGGER.debug(">> Xml sent");
        kafkaProcessor.submitXMLSample();
        return "XML Message sent";
    }

    @RequestMapping("/submitMetadata")
    public String submitMetadata() {
        LOGGER.debug(">> metadata sent");
        kafkaProcessor.submitMetadata();
        return "metadata sent";
    }

    @RequestMapping("/submitMetadataX/{count}")
    public String submitMetadataX(@PathVariable int count) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        LOGGER.debug(">> kafka submit metadata-[{}] received", count);
        for (int i = 0; i < count; i++) {
            kafkaProcessor.submitMetadata();
        }
        return "metadata-X submitted to kafka:" + count;
    }

    @RequestMapping("/publishMetadata")
    public String publishMetadata() {
        LOGGER.debug("[{}] REST call received.", Thread.currentThread().getName());
        messageProcessor.publishMetadata(sampleMessageService.getMETADATA());
        return "metadata published";
    }

    @RequestMapping("/publishMetadataX/{count}")
    public String publishMetadataX(@PathVariable int count) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        LOGGER.debug("[{}] Metadata-X call received.", Thread.currentThread().getName());
        for (int i = 0; i < count; i++) {
            messageProcessor.publishMetadata(sampleMessageService.getRandMetadata());
        }
        LOGGER.debug("Metadata-X completed publishing in:{}", stopwatch);
        return "metadata published->" + count + " in " + stopwatch.stop();
    }

    @RequestMapping("/metrics")
    public Map<String, Double> metrics() {
        return kafkaProcessor.metrics();
    }
}
