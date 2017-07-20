package org.ameet.kafkasample.repository;


import org.ameet.kafkasample.model.MessageMetadata;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ameet.chaubal on 7/20/2017.
 */
public interface MetadataRepository extends CrudRepository<MessageMetadata, Long> {
}
