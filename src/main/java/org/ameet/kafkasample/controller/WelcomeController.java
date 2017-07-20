package org.ameet.kafkasample.controller;

import org.ameet.kafkasample.model.Greet;
import org.ameet.kafkasample.service.EZMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameet.chaubal on 6/12/2017.
 * for the sample welcome model
 */
@RestController
public class WelcomeController {
    private static final String TEMPLATE = "howdy %s!";
    Random random = new Random();
    private AtomicInteger index = new AtomicInteger(0);
    @Autowired
    private EZMessageProcessor ezMessageProcessor;

    @RequestMapping("/greet")
    public Greet greet(@RequestParam(value = "content", defaultValue = "sup!") String greeting) {
        return new Greet(index.incrementAndGet(), String.format(TEMPLATE, greeting));
    }

    @GetMapping("/publishTen")
    public String publish() throws InterruptedException {
        int breakPoint = 1 + random.nextInt(8);
        int sleep = 1000 * (random.nextInt(5) + 1);
        System.out.println("Sleeping for ..." + sleep + " BreakPoint @ = " + breakPoint);

        for (int i = 0; i < 10; i++) {
            ezMessageProcessor.publish(Thread.currentThread().getName() + ":" + i);
            if (i == breakPoint) {
                Thread.sleep(sleep);
            }
        }
        return "Done.";
    }
}
