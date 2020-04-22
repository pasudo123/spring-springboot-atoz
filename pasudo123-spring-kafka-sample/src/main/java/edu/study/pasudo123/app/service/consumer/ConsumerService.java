package edu.study.pasudo123.app.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "test-topicAAA", groupId = "aaaGroup")
    public void listen1(@Payload String message){
        log.info("t[AAA] => {}", message);
    }

    @KafkaListener(topics = "test-topicBBB", groupId = "bbbGroup")
    public void listen2(@Payload String message) {
        log.info("t[BBB] =====> {}", message);
    }

    @KafkaListener(topics = "test-topicCCC", groupId = "cccGroup")
    public void listen3(@Payload String message) { log.info("t[CCC] ==========> {}", message); }
}
