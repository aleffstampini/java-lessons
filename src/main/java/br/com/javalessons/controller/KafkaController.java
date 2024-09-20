package br.com.javalessons.controller;

import br.com.javalessons.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/send")
    public ResponseEntity sendMessage(@RequestParam("message") String message) {
        this.kafkaProducerService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}