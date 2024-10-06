package com.management.user;

import com.management.user.dto.PasswordEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventPublisher implements EventPublisher {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(Object event) {
        if (event instanceof PasswordEvent) {
            PasswordEvent passwordCreatedEvent = (PasswordEvent) event;
            kafkaTemplate.send("password.created", passwordCreatedEvent.getEmail(), passwordCreatedEvent.getPassword());
        }
    }
}
