package org.ameet.kafkasample.dao;

import com.google.common.base.Stopwatch;
import org.ameet.kafkasample.model.MessageMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ameet.chaubal on 7/20/2017.
 * mainly to perform batch operations
 */
@Component
public class MetadataDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataDAO.class);
    private static final String INSERT_SQL = "INSERT INTO message_metadata(created_timestamp," +
            "message_id, operation_name, requestor_id, routing, service_name, target_system, transaction_id, type) " +
            "VALUES " +
            "(?,?,?,?,?,?,?,?,?)";
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
        LOGGER.debug("Metadata batch insert via EntityManager. # records:{} in -> {}", metadataList.size(), stopwatch
                .stop());
    }

    @Transactional
    public void batchInsertByTemplate(List<MessageMetadata> metadataList) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setObject(1, metadataList.get(i).getCreatedTimestamp());
                ps.setString(2, metadataList.get(i).getMessageId());
                ps.setString(3, metadataList.get(i).getOperationName());
                ps.setString(4, metadataList.get(i).getRequestorId());
                ps.setString(5, metadataList.get(i).getRouting());

                ps.setString(6, metadataList.get(i).getServiceName());
                ps.setString(7, metadataList.get(i).getTargetSystem());
                ps.setString(8, metadataList.get(i).getTransactionId());
                ps.setString(9, metadataList.get(i).getType());
            }

            @Override
            public int getBatchSize() {
                return dbBatchSize;
            }
        });
        LOGGER.debug("Metadata batch insert via template # records:{} in -> {}", metadataList.size(), stopwatch.stop());
    }
}
