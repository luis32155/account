package com.exercise.account_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic clienteTopic() {
        return new NewTopic("cliente-topic", 1, (short) 1);
    }
}