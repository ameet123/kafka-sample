package org.ameet.kafkasample;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.zip.InflaterInputStream;

/**
 * Created by ameet.chaubal on 6/12/2017.
 */
public class Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
    private static final int DEFLATE_BUFFER = 1024;

    public static void delay(long milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            LOGGER.error("delay interrupted", e);
        }
    }

    public static String fileToString(String file) {
        URL url = Resources.getResource(file);
        String text = null;
        try {
            text = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("ERR: reading file to string");
        }
        return text;
    }

    public static String decodeDeflate(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(decoded));
        StringBuilder result = new StringBuilder();
        byte[] buf = new byte[DEFLATE_BUFFER];
        int rlen = -1;
        try {
            while ((rlen = iis.read(buf)) != -1) {
                result.append(new String(Arrays.copyOf(buf, rlen)));
            }
        } catch (IOException e) {
            LOGGER.error("Err reading encoded inflated string, returning original:{}\n{}", e.getMessage(), encoded);
            return encoded;
        }
        return result.toString();
    }

    public static JsonNode recursiveSearch(JsonNode node, String entityName) {
        if (node == null) {
            return null;
        }
        if (node.has(entityName)) {
            return node.get(entityName);
        }
        if (!node.isContainerNode()) {
            return null;
        }
        for (JsonNode child : node) {
            if (child.isContainerNode()) {
                JsonNode childResult = recursiveSearch(child, entityName);
                if (childResult != null && !childResult.isMissingNode()) {
                    return childResult;
                }
            }
        }
        return null;
    }
}
