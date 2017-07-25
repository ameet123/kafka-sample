package org.ameet.kafkasample.controller;

import com.google.common.base.Stopwatch;
import org.ameet.kafkasample.model.KMessage;
import org.ameet.kafkasample.model.SubmitStatus;
import org.ameet.kafkasample.service.KafkaProcessor;
import org.ameet.kafkasample.service.MessageProcessor;
import org.ameet.kafkasample.service.SampleMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
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
    private final KafkaProcessor kafkaProcessor;
    private AtomicInteger index = new AtomicInteger(0);
    private SampleMessageService sampleMessageService;
    private MessageProcessor messageProcessor;

    @Autowired
    public MessagingController(KafkaProcessor kafkaProcessor, SampleMessageService sampleMessageService,
                               MessageProcessor messageProcessor) {
        this.kafkaProcessor = kafkaProcessor;
        this.sampleMessageService = sampleMessageService;
        this.messageProcessor = messageProcessor;
    }

    @RequestMapping("/submit/{content}")
    public DeferredResult<Integer> submitSync(@PathVariable String content) {
        LOGGER.debug(">> received message on kafka submitSync...");
        DeferredResult<Integer> deferred = new DeferredResult<Integer>(90000L);
        CompletableFuture<Integer> f = kafkaProcessor.submit(content);
        f.whenComplete((res, ex) -> {
            if (ex != null) {
                deferred.setErrorResult(ex);
            } else {
                deferred.setResult(res);
            }
        });
        return deferred;
    }

    @RequestMapping("/submitAsync/{content}")
    public String submitAsync(@PathVariable String content) {
        LOGGER.debug(">> received message on kafka submitAsync...");
        kafkaProcessor.submitAsync(content);
        return "Message sent";
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
            messageProcessor.publishMetadata(sampleMessageService.getMETADATA());
        }
        LOGGER.debug("Metadata-X completed publishing in:{}", stopwatch);
        return "metadata published->" + count + " in " + stopwatch.stop();
    }

    @RequestMapping("/metrics")
    public Map<String, Double> metrics() {
        return kafkaProcessor.metrics();
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<SubmitStatus> send(@RequestBody KMessage message) {
        LOGGER.debug(">> received POST message on kafka send...");
        SubmitStatus status = new SubmitStatus();
        status.setId(message.getId());
        status.setMessage(message.getContent());
        status.setKafkaTopic(kafkaProcessor.getSimpleTopic());
        Stopwatch stopwatch = Stopwatch.createStarted();
        kafkaProcessor.submitAsync(message.getContent());
        status.setTotalSent(kafkaProcessor.getSentCount().get());
        status.setElapsedMilli((int) stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
