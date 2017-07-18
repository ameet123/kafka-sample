package org.ameet.kafkasample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ameet.chaubal on 7/18/2017.
 * Based on the attributes inside kafka message, determine the type of the message
 * so that appropriate processing and unmarshalling logic may be applied
 */
@Component
public class MessageTypeDetectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTypeDetectionService.class);

    public void detectMessageType(String message){
        MessageType type;
        if(message.contains("xml")){
            type = MessageType.EZMessage;
        }
    }

}
