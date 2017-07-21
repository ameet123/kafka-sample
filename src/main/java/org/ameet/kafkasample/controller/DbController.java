package org.ameet.kafkasample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ameet.kafkasample.Util;
import org.ameet.kafkasample.dao.MetadataDAO;
import org.ameet.kafkasample.model.KafkaMessage;
import org.ameet.kafkasample.model.MessageMetadata;
import org.ameet.kafkasample.model.entity.User;
import org.ameet.kafkasample.repository.MetadataRepository;
import org.ameet.kafkasample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameet.chaubal on 7/20/2017.
 */
@RestController
@RequestMapping("/db")
public class DbController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);
    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MetadataDAO metadataDAO;

    @GetMapping(path = "/addUser")
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/addMetadata")
    @Transactional
    public String addSampleMessageMetadata() {
        String metadata = Util.fileToString("messageMetadata.json");
        KafkaMessage kafkaMessage;
        try {
            kafkaMessage = mapper.readValue(metadata, KafkaMessage.class);
        } catch (IOException e) {
            LOGGER.error("ERR: unmarshalling metadata json:\n{}\n", metadata);
            return "Unmarshal error";
        }
        LOGGER.debug("----->{}", kafkaMessage.getMessageMetadata().getChannelId());

        List<MessageMetadata> messageMetadataList = new ArrayList<>();
        messageMetadataList.add(kafkaMessage.getMessageMetadata());
        metadataDAO.batchInsertByTemplate(messageMetadataList);

        return "Saved:" + kafkaMessage.getMessageMetadata().getMessageId();
    }
}
