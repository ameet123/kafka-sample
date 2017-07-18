package org.ameet.kafkasample;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
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

    public static String fileToString(String file){
        URL url = Resources.getResource(file);
        String text = null;
        try {
            text = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("ERR: reading file to string");
        }
        return text;
    }
}
