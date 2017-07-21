package org.ameet.kafkasample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ameet.kafkasample.service.MessageProcessor;
import org.ameet.kafkasample.service.MessageType;
import org.ameet.kafkasample.service.MessageTypeDetectionService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ameet Chaubal on 5/18/2017.
 */
@Configuration
@EnableKafka
@EnableAsync
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    private static final int CONCURRENCY_LIMIT = 10;
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${group.simple}")
    private String simpleGroup;
    @Value(value = "${topic.simple.name}")
    private String simpleTopic;
    @Autowired
    private MessageProcessor messageProcessor;
    @Autowired
    private MessageTypeDetectionService messageTypeDetectionService;

    @Bean
    public TaskExecutor getAsyncExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(CONCURRENCY_LIMIT);
        executor.setThreadNamePrefix("Kafka-");
        return executor;
    }


    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

    // Consumer
    @Bean
    public ConsumerFactory<String, String> simpleConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, simpleGroup);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(simpleConsumerFactory());
        return factory;
    }

    @KafkaListener(topics = "${topic.simple.name}", group = "${group.simple}")
    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOGGER.info(">>>>>>>[part-{}]: Received Messasge in group:{}   ", partition, simpleGroup);
        MessageType type = messageTypeDetectionService.detectMessageType(message);
        LOGGER.info("Message Type==>{}", type);
    }

    /**
     * each work item is short lived . just do the work and get out, so threadpoolexecutor is better than simpletask
     * executor
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor(@Value("${app.rx.threadpool.max}") int maxSize) {
        LOGGER.debug("Max pool size:{}", maxSize);
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        poolExecutor.setMaxPoolSize(maxSize);
        poolExecutor.setThreadNamePrefix("RXbus-");
        return poolExecutor;
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}