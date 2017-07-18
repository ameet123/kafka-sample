package org.ameet.kafkasample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by ameet.chaubal on 7/18/2017.
 * Based on the attributes inside kafka message, determine the type of the message
 * so that appropriate processing and unmarshalling logic may be applied
 */
@Component
public class MessageTypeDetectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTypeDetectionService.class);
    private static final Pattern RESERV_PATTERN = Pattern.compile("(?=.*\\{.*)(?=hotelReservation)", Pattern.DOTALL);
    private static final Pattern EZ_REFRESH_PATTERN = Pattern.compile("(?=.*\\{.*)(?=Refresh)", Pattern.DOTALL);

    public MessageType detectMessageType(String message) {
        MessageType type = null;
        if (isXmlMessage(message)) {
            type = MessageType.XML;
        } else if (isJsonReservation(message)) {
            type = MessageType.HOTEL_RESERVATION;
        } else if (isEZRefresh(message)) {
            type = MessageType.EZ_REFRESH;
        }
        return type;
    }

    private boolean isXmlMessage(String message) {
        return message.contains("xml");
    }

    private boolean isJsonReservation(String message) {
        return RESERV_PATTERN.matcher(message).find();
    }

    private boolean isEZRefresh(String message) {
        return EZ_REFRESH_PATTERN.matcher(message).find();
    }

}
