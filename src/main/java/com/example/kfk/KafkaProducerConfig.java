package com.example.kfk;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {

    public static final String TOPIC_NAME = "dh.kfk.topic";

    @Value("${kafka-url}")
    private String kafkaUrl;

    @Bean
    public NewTopic oneTopic() {
        return TopicBuilder
                .name(TOPIC_NAME)
                .partitions(2)
                .build();
    }

    @Bean
    public KafkaTemplate<String, Sms> kafkaTemplate() {
        var properties = Map.<String, Object>of(
                BOOTSTRAP_SERVERS_CONFIG, kafkaUrl,
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        );
        var factory = new DefaultKafkaProducerFactory<String, Sms>(properties);
        return new KafkaTemplate<>(factory);
    }
}
