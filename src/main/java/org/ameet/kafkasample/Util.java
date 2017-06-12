package org.ameet.kafkasample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by ameet.chaubal on 6/12/2017.
 */
public class Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    public static void delay(long milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            LOGGER.error("delay interrupted", e);
        }
    }
}
