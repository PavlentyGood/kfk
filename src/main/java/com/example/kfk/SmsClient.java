package com.example.kfk;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SmsClient {

    private final KafkaTemplate<String, Sms> kafkaTemplate;

    public void sendSms(Sms sms) {
        kafkaTemplate.send(KafkaProducerConfig.TOPIC_NAME, sms);
    }
}
