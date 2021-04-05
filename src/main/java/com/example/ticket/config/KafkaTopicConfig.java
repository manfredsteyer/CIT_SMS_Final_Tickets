package com.example.ticket.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private static final Logger log = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Bean
    public KafkaAdmin kafkaAdmin() {
        log.info("kafkaAdmin", this.bootstrapAddress);
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topicTicketBooked() {
        log.info("topicTicketBooked", this.bootstrapAddress);

         return new NewTopic("ticketBooked", 1, (short) 1);
    } 
}
