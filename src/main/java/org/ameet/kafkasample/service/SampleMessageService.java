package org.ameet.kafkasample.service;

import org.ameet.kafkasample.Util;
import org.springframework.stereotype.Component;

/**
 * Created by ameet.chaubal on 7/24/2017.
 * generate sample message from file
 */
@Component
public class SampleMessageService {
    private static String XML_MODIFY_REQ_MSG;
    private static String EZ_SAMPLE_MSG;
    private static String RESERV_SAMPLE_MSG;
    private static String METADATA;

    public SampleMessageService() {
        METADATA = Util.fileToString("messageMetadata.json");
    }

    public String getMETADATA() {
        return METADATA;
    }
}
