package org.ameet.kafkasample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Ameet Chaubal on 5/18/2017.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException {
       ConfigurableApplicationContext appCtx =  SpringApplication.run(Application.class, args);
        Thread.sleep(5000L);
        appCtx.close();

    }
}
