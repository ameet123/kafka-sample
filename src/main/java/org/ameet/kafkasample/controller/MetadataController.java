package org.ameet.kafkasample.controller;

import org.ameet.kafkasample.dao.MetadataDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ameet.chaubal on 7/27/2017.
 * for enhanced jpa queries not serviced by data-rest controller
 */
@RestController
@RequestMapping("/metadata/enhanced")
public class MetadataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataController.class);
    private final MetadataDAO metadataDAO;

    @Autowired
    public MetadataController(MetadataDAO metadataDAO) {
        this.metadataDAO = metadataDAO;
    }

    @RequestMapping(value = "/channels", produces = "application/json")
    public List<Object> getDistinctChannels() {
        return metadataDAO.getDistinctChannels();
    }
    @RequestMapping(value = "/service", produces = "application/json")
    public List<Object> getDistinctService() {
        return metadataDAO.getDistinctService();
    }
    @RequestMapping(value = "/routing", produces = "application/json")
    public List<Object> getDistinctRouting() {
        return metadataDAO.getDistinctRouting();
    }
}
