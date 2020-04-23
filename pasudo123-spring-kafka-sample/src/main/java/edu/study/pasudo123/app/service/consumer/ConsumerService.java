//package edu.study.pasudo123.app.service.consumer;
//
//import edu.study.pasudo123.app.payload.Dummy;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class ConsumerService {
//
//    @KafkaListener(topics = "test-topicAAA", containerFactory = "kafkaListenerContainerFactoryOnAAA")
//    public void listen1(@Payload String message){
//        log.info("t[AAA] => {}", message);
//    }
//
//    @KafkaListener(topics = "topic-dummy", containerFactory = "dummyKafkaListenerContainerFactory")
//    public void listenDummy(@Payload Dummy dummy){
//        log.info("==> {}", dummy.toString());
//    }
//
////    @KafkaListener(topics = "test-topicBBB", containerFactory = "kafkaListenerContainerFactoryOnBBB")
////    public void listen2(@Payload String message) {
////        log.info("t[BBB] =====> {}", message);
////    }
//
////    @KafkaListener(topics = "test-topicCCC", containerFactory = "kafkaListenerContainerFactoryOnCCC")
////    public void listen3(@Payload String message) { log.info("t[CCC] ==========> {}", message); }
//}
