//package edu.study.pasudo123.app.config;
//
//import edu.study.pasudo123.app.payload.Dummy;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 컨슈머 설정
// */
//@EnableKafka
//@Configuration
//public class kafkaConsumerConfiguration {
//
//    @SuppressWarnings("Duplicates")
//    @Bean
//    public ConsumerFactory<String, String> consumerFactoryOnAAA(){
//        /** groupId : AAA **/
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "aaaGroup");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // 가장 빠른 오프셋으로 설정 (컨슈머 입장에서 가장 오래된)
//
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryOnAAA() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactoryOnAAA());
//        factory.setConcurrency(1);
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, Dummy> loadedConsumerFactory(){
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "dummyGroup");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Dummy.class);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Dummy> dummyKafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, Dummy> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(loadedConsumerFactory());
//        factory.setConcurrency(3);
//        return factory;
//    }
//
////    @SuppressWarnings("Duplicates")
////    @Bean
////    public ConsumerFactory<String, String> consumerFactoryOnBBB() {
////        /** groupId : BBB **/
////        Map<String, Object> props = new HashMap<>();
////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////        props.put(ConsumerConfig.GROUP_ID_CONFIG, "bbbGroup");
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");   // 최신 오프셋을 자동 설정
////
////        return new DefaultKafkaConsumerFactory<>(props);
////    }
////
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryOnBBB() {
////        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactoryOnBBB());
////        factory.setConcurrency(2);
////        return factory;
////    }
////
////    @SuppressWarnings("Duplicates")
////    @Bean
////    public ConsumerFactory<String, String> consumerFactoryOnCCC() {
////        /** groupId : CCC **/
////        Map<String, Object> props = new HashMap<>();
////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////        props.put(ConsumerConfig.GROUP_ID_CONFIG, "cccGroup");
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");   // 최신 오프셋을 자동 설정
////
////        return new DefaultKafkaConsumerFactory<>(props);
////    }
////
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryOnCCC() {
////        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactoryOnCCC());
////        factory.setConcurrency(3);
////        return factory;
////    }
//}
