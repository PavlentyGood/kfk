package com.example.kfk;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class SmsReceiver {

    @KafkaListener(topics = KafkaProducerConfig.TOPIC_NAME)
    public void receive(Sms sms) {
        log.info("Sms received: phone={}, message={}", sms.phone(), sms.message());
    }
}
