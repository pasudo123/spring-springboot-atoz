package edu.study.pasudo123.app.quick;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * reference : https://docs.spring.io/spring-kafka/reference/html/#a-very-very-quick-example
 */
@ExtendWith(SpringExtension.class)
public class QuickSimpleTest {

    /**
     * Connection refused: no further information. 문제로 종료됨.
     */

    private static final String LOCALHOST = "localhost:9092";
    private final Logger logger = LoggerFactory.getLogger(QuickSimpleTest.class);

    @Test
    @DisplayName("카프카 센더 및 리시버 간단 테스트")
    public void testAuthCommit() throws Exception {

        logger.info("=============================");
        logger.info("Start auto");

        final ContainerProperties containerProperties = new ContainerProperties("topic1", "topic2");
        final CountDownLatch latch = new CountDownLatch(4);

        containerProperties.setMessageListener((MessageListener<Integer, String>) message -> {
            logger.info("Received : {}", message);
            latch.countDown();
        });

        /** consumer **/
        KafkaMessageListenerContainer<Integer,String> container = createContainer(containerProperties);

        container.setBeanName("testAuto");
        container.start();

        /** 컨테이너가 시작되기 이전에 1초 정도 여유를 주기위함 **/
        Thread.sleep(1000);

        /** producer **/
        KafkaTemplate<Integer, String> template = createTemplate();
        template.setDefaultTopic("topic1");
        template.sendDefault(0, "foo");
        template.sendDefault(2, "bar");
        template.sendDefault(0, "baz");
        template.sendDefault(2, "qux");
        template.flush();

        Assertions.assertThat(latch.await(60, TimeUnit.SECONDS)).isTrue();

        container.stop();

        logger.info("=============================");
        logger.info("Stop auto");
    }

    private KafkaMessageListenerContainer<Integer, String> createContainer(final ContainerProperties containerProperties) {


        final DefaultKafkaConsumerFactory<Integer, String> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumeProps());

        return new KafkaMessageListenerContainer<>(kafkaConsumerFactory, containerProperties);
    }

    private KafkaTemplate<Integer, String> createTemplate() {

        final Map<String, Object> senderProps = senderProps();
        final ProducerFactory<Integer, String> producerFactory = new DefaultKafkaProducerFactory<>(senderProps());

        return new KafkaTemplate<>(producerFactory);
    }


    private Map<String, Object> consumeProps() {

        /**
         * reference : https://memorynotfound.com/spring-kafka-consume-producer-example/ 설명글 참조.
         * **/

        /** consumer 설정 **/

        final Map<String, Object> props = new HashMap<>();

        // 카프카 클러스터 최초 연결에 대한 호스트 및 포트 목록을 지정. 해당 목록은 콤마로 구분되며 아래의 형식을 따른다.
        // host1:port1,host2:port2 ...
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCALHOST);

        // 해당 컨슈머가 속한 컨슈머 그룹을 식별하는 고유한 문자열 설정
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo_group");

        // 자동 커밋 설정, 컨슈머는 poll() 을 호출할 시 가장 마지막 오프셋을 커밋.
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");

        // 단일 호출 poll() 에 대해서 최대 레코드 수를 지정.
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);

        // 카프카에 초기 오프셋이 없거나 서버에 현재 오프셋이 존재하지 않는 경우에 수행할 작업을 명시.
        // - earliest : 가장 빠른 오프셋으로 자동설정
        // - latest : 최신 오프셋으로 자동설정
        // - none : 이전 오프셋이 없으면 에러 throws
        // - anything else : 에러 throws
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // 키 역직렬화 클래스 지정
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);

        // 밸류 역직렬화 클래스 지정
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return props;
    }

    private Map<String ,Object> senderProps() {

        /** producer 설정 **/

        final Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCALHOST);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }
}
