package org.ameet.kafkasample.service;

import org.ameet.kafkasample.Util;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private List<String> metadataList = new ArrayList<>();
    private Random rand = new Random();

    public SampleMessageService() {
        METADATA = Util.fileToString("messageMetadata.json");
//        metadataList.add(METADATA);
        metadataList.add(Util.fileToString("messageMetadata-7-28.json"));
//        metadataList.add(Util.fileToString("messageMetadata-8-5.json"));
//        metadataList.add(Util.fileToString("messageMetadata-8-14.json"));
    }

    public String getRandMetadata() {
        return metadataList.get(rand.nextInt(metadataList.size()));
    }

    public String getMETADATA() {
        return METADATA;
    }
}
