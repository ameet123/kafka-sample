package org.ameet.kafkasample.repository;


import org.ameet.kafkasample.model.MessageMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by ameet.chaubal on 7/20/2017.
 * queries for searching via REST
 */
@RepositoryRestResource(path = "metadata", exported = true)
public interface RestMetadataRepository extends PagingAndSortingRepository<MessageMetadata, Integer> {
    @RestResource(path = "findTxnId")
    List<MessageMetadata> findByTransactionId(@Param("transactionId") String transactionId);

    @RestResource(path = "countByDateRange")
    int countByCreatedTimestampBetween(@DateTimeFormat(pattern = "yyyy-MM-dd") @Param("start") Date start,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd") @Param("end") Date end);

    @RestResource(path = "countByChannel")
    int countByChannelId(@Param("channelId") String channelId);

    @RestResource(path = "countbyOperation")
    int countByOperationName(@Param("operation") String operation);

    int countByRouting(@Param("routing") String routing);

    @RestResource(path = "countByService")
    int countByServiceName(@Param("service") String service);

    @RestResource(path = "countByTarget")
    int countByTargetSystem(@Param("target") String target);

    @RestResource(path = "countByChannelAndDateRange")
    int countByChannelIdAndCreatedTimestampBetween(@Param("channelId") String channelId,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") @Param("start") Date start,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") @Param("end") Date end);

    @RestResource(path = "countByOperationAndDateRange")
    int countByOperationNameAndCreatedTimestampBetween(
            @Param("operation") String operation,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @Param("start") Date start,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @Param("end") Date end);
}
