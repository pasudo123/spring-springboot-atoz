//package edu.study.pasudo123.app.server.producer;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Service
//@RequiredArgsConstructor
//public class ProducerService {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    private static final String TOPIC_AAA = "test-topicAAA";
//    private AtomicInteger atoNumber1 = new AtomicInteger(0);
//
//    private static final String TOPIC_BBB = "test-topicBBB";
//    private AtomicInteger atoNumber2 = new AtomicInteger(0);
//
//    private static final String TOPIC_CCC = "test-topicCCC";
//    private AtomicInteger atoNumber3 = new AtomicInteger(0);
//
//
//    public void sendMessageToTopic1(String message){
//        kafkaTemplate.send(TOPIC_AAA, message);
//    }
//
//    public void sendMessageToTopic2(String message){
//        kafkaTemplate.send(TOPIC_BBB, message);
//    }
//
//    public void sendMessageToTopic3(String message){
//        kafkaTemplate.send(TOPIC_CCC, message);
//    }
//
//    /**
//     * send() 이후에 콜백으로 전환 시 아래 링크 참고.
//     * https://www.baeldung.com/spring-kafka#2-publishing-messages
//     */
//
//    @Scheduled(fixedRate = 2000L)
//    public void publishMessageOnAAA() {
//        sendMessageToTopic1("topicsAAA [" + atoNumber1.getAndAdd(1) + "]");
//    }
//
//    @Scheduled(fixedRate = 5000L)
//    public void publishMessageOnBBB() {
//        sendMessageToTopic2("topicsBBB [" + atoNumber2.getAndAdd(1) + "]");
//    }
//
//    @Scheduled(fixedRate = 10000L)
//    public void publishMessageOnCCC() {
//        sendMessageToTopic3("topicsCCC [" + atoNumber3.getAndAdd(1) + "]");
//    }
//}
