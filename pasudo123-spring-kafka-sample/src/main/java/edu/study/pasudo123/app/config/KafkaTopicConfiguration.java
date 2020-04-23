package edu.study.pasudo123.app.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * 토픽 생성
 */
@Configuration
public class KafkaTopicConfiguration {

    private static final String TOPIC_AAA = "test-topicAAA";
    private static final String TOPIC_BBB = "test-topicBBB";
    private static final String TOPIC_CCC = "test-topicCCC";
    private static final String TOPIC_DUMMY = "topic-dummy";

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1(){
        return new NewTopic(TOPIC_AAA, 1, Short.valueOf("1"));
    }

    @Bean
    public NewTopic topic2(){
        return new NewTopic(TOPIC_BBB, 1, Short.valueOf("1"));
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic(TOPIC_CCC, 1, Short.valueOf("1"));
    }

    @Bean
    public NewTopic topic4(){
        return new NewTopic(TOPIC_DUMMY, 1, Short.valueOf("1"));
    }
}
