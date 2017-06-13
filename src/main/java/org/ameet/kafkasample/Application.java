package org.ameet.kafkasample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Ameet Chaubal on 5/18/2017.
 */
@SpringBootApplication
//@EnableAsync
public class Application {

    public static void main(String[] args) throws InterruptedException {
       ConfigurableApplicationContext appCtx =  SpringApplication.run(Application.class, args);
    }
}
