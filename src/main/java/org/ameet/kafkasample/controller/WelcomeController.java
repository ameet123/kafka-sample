package org.ameet.kafkasample.controller;

import org.ameet.kafkasample.model.Greet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameet.chaubal on 6/12/2017.
 * for the sample welcome model
 */
@RestController
public class WelcomeController {
    private static final String TEMPLATE = "howdy %s!";
    private AtomicInteger index = new AtomicInteger(0);

    @RequestMapping("/greet")
    public Greet greet(@RequestParam(value = "content", defaultValue = "sup!") String greeting) {
        return new Greet(index.incrementAndGet(), String.format(TEMPLATE, greeting));
    }
}
