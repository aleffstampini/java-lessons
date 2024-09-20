package br.com.javalessons.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "default")
    public void listen(String message) {
        log.info("Received message: {}", message);
    }
}
