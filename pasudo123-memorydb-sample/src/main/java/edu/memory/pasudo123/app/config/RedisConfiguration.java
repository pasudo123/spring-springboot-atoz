package edu.memory.pasudo123.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.memory.pasudo123.app.queue.MessagePublisher;
import edu.memory.pasudo123.app.queue.RedisMessagePublisher;
import edu.memory.pasudo123.app.queue.RedisMessageSubscriber;
import edu.memory.pasudo123.app.student.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by pasudo123 on 2019-11-09
 * Email: oraedoa@gmail.com
 **/
@Configuration
@EnableRedisRepositories(basePackageClasses = {StudentRepository.class})
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        /** 1.x
         @Deprecated
         JedisConnectionFactory jedisConnectionFactory =  new JedisConnectionFactory();
         jedisConnectionFactory.setHostName("localhost");
         jedisConnectionFactory.setPort(6379);
         **/

        /** 2.x **/
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));

        return template;
    }

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    public RedisMessageListenerContainer redisContainer() {

        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());

        return container;
    }

    @Bean
    public MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate(), topic());
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }

}
