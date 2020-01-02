package edu.study.pasudo123.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ 의 Queue 로 부터의 리스너 역할을 수행하는 클래스
 */
@Service
@Slf4j
public class RabbitMqListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

        log.info("=====================================================");
        log.info("Consuming Message : {}", new String(message.getBody()));
    }
}
