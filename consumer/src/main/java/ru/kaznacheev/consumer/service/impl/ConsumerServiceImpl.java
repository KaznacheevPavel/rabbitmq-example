package ru.kaznacheev.consumer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.kaznacheev.consumer.service.ConsumerService;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getMessage(String message) {
        log.info("Получено сообщение: \"" + message + "\"");
    }

}
