package edu.study.pasudo123.app.config;

import edu.study.pasudo123.app.service.RabbitMqListener;
import lombok.Getter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String hostName;

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * [디폴트] 커넥션 팩토리를 통한 메시지 리스너 컨테이너 빈 생성
     */
    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue());
        container.setMessageListener(new RabbitMqListener());

        return container;
    }

    /**
     * [커스텀] 커넥션 팩토리 빈 생성
     */
//    @Bean
//    public ConnectionFactory connectionFactory() {
//
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(hostName);
//        cachingConnectionFactory.setUsername(username);
//        cachingConnectionFactory.setPassword(password);
//
//        return cachingConnectionFactory;
//    }
}
