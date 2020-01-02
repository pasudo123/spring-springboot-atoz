package edu.study.pasudo123.app.service;

import edu.study.pasudo123.app.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqSender {

    private final AmqpTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    public void send(Employee employee) {

        rabbitTemplate.convertAndSend(exchange, routingKey, employee);
        log.info("Send Message : {}", employee.toString());
    }
}
