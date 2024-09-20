package br.com.javalessons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "default";

    public void sendMessage(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
}
