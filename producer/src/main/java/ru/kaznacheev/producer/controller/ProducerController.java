package ru.kaznacheev.producer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kaznacheev.producer.service.ProducerService;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
@Slf4j
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping
    public void sendMessageToBroker(@RequestBody String message) {
        producerService.send(message);
    }

}
