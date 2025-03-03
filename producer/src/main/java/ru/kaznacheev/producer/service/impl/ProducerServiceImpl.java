package ru.kaznacheev.producer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kaznacheev.producer.service.ProducerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Override
    public void send(String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Сообщение: \"" + message + "\" отправлено в брокер");
    }

}
