package org.ameet.kafkasample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ameet.kafkasample.model.ihg.EZMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
@Component
public class EZMessageProcessor {
    private ObjectMapper mapper = new ObjectMapper();

    public EZMessage unmarshalEz(String message) throws IOException {
        return mapper.readValue(message, EZMessage.class);
    }
}
