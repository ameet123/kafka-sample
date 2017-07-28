package org.ameet.kafkasample.dao;

import com.google.common.base.Stopwatch;
import org.ameet.kafkasample.model.MessageMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ameet.chaubal on 7/20/2017.
 * mainly to perform batch operations
 */
@Component
public class MetadataDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataDAO.class);
    private static final String INSERT_SQL = "INSERT INTO message_metadata(channel_id, created_timestamp," +
            "message_id, operation_name, requestor_id, routing, service_name, target_system, transaction_id, type, " +
            "cf_number) " +
            "VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DISTINCT_CHANNELS_SQL = "SELECT DISTINCT m.channel_Id FROM Message_Metadata m";
    private static final String DISTINCT_SERVICE_SQL = "SELECT DISTINCT m.service_name FROM Message_Metadata m";
    private static final String DISTINCT_ROUTING_SQL = "SELECT DISTINCT m.routing FROM Message_Metadata m";

    private final JdbcTemplate jdbcTemplate;
    private int dbBatchSize;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MetadataDAO(JdbcTemplate jdbcTemplate, @Value("${db.batch.size}") int dbBatchSize) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbBatchSize = dbBatchSize;
    }

    @Transactional
    public void batchInsertByEm(List<MessageMetadata> metadataList) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < metadataList.size(); i++) {
            em.persist(metadataList.get(i));
            if (i % dbBatchSize == 0) {
                LOGGER.debug(">> DAO-> flushing batch");
                em.flush();
                em.clear();
            }
        }
        LOGGER.debug("Metadata batch insert via EntityManager # records:{} in -> {}", metadataList.size(), stopwatch
                .stop());
    }

    @Transactional
    public void batchInsertByTemplate(List<MessageMetadata> metadataList) {
        if (metadataList == null || metadataList.size() == 0) {
            LOGGER.warn("JDBC Batch insert: incoming data null/zero, returning");
            return;
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            jdbcTemplate.batchUpdate(INSERT_SQL, metadataList, dbBatchSize,
                    (ps, metadata) -> {
                        ps.setObject(1, metadata.getChannelId());
                        ps.setObject(2, metadata.getCreatedTimestamp());
                        ps.setString(3, metadata.getMessageId());
                        ps.setString(4, metadata.getOperationName());
                        ps.setString(5, metadata.getRequestorId());
                        ps.setString(6, metadata.getRouting());
                        ps.setString(7, metadata.getServiceName());
                        ps.setString(8, metadata.getTargetSystem());
                        ps.setString(9, metadata.getTransactionId());
                        ps.setString(10, metadata.getType());
                        ps.setString(11, metadata.getCfNumber());
                    });
        } catch (DataAccessException e) {
            LOGGER.error("ERR: jdbc error inserting batch", e);
        }
        LOGGER.debug("Metadata batch insert via template # records:{} in -> {}", metadataList.size(), stopwatch.stop());
    }

    /**
     * since the query returns a single column, it needs to have List<Object> and not Object[]
     * also Sqlresult mapping does not work unless it's an entity.
     * @return
     */
    public List<Object> getDistinctChannels() {
        return getSingleColumnStringSql(DISTINCT_CHANNELS_SQL);
    }
    public List<Object> getDistinctService() {
        return getSingleColumnStringSql(DISTINCT_SERVICE_SQL);
    }
    public List<Object> getDistinctRouting() {
        return getSingleColumnStringSql(DISTINCT_ROUTING_SQL);
    }

    private List<Object> getSingleColumnStringSql(String sql){
        Query query = em.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object> results = query.getResultList();
        LOGGER.debug("Single column query sql-Items # {}", results.size());
        return results;
    }
}
