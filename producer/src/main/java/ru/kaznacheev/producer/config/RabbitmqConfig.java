package ru.kaznacheev.producer.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class RabbitmqConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("Сообщение с ID: {} было доставлено в обменник", correlationData.getId());
            } else {
                log.error("Сообщение с ID: {} не доставлено в обменник. {}", correlationData.getId(), cause);
            }
        });
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.error("Сообщение с ID: {} не было доставлено в очередь через обменник {}",
                    returnedMessage.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation"),
                    returnedMessage.getExchange());
        });
        return rabbitTemplate;
    }

}
