package com.example.kfk;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@RequiredArgsConstructor
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static final String GROUP = "dh.kfk.consumer";

    @Value("${kafka-url}")
    private String kafkaUrl;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Sms> kafkaListenerContainerFactory() {
        var properties = Map.<String, Object>of(
                BOOTSTRAP_SERVERS_CONFIG, kafkaUrl,
                GROUP_ID_CONFIG, GROUP
        );

        var consumerFactory = new DefaultKafkaConsumerFactory<>(
                properties,
                new StringDeserializer(),
                new JsonDeserializer<>(Sms.class));

        var listenerFactory = new ConcurrentKafkaListenerContainerFactory<String, Sms>();
        listenerFactory.setConsumerFactory(consumerFactory);
        return listenerFactory;
    }
}
