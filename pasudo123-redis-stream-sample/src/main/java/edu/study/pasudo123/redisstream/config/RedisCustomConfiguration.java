package edu.study.pasudo123.redisstream.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.stream.StreamReceiver;

@Configuration
@EnableConfigurationProperties(PollConfiguration.class)
public class RedisCustomConfiguration {

    // https://github.com/mp911de/redis-stream-demo/blob/master/redis-stream-demo/src/main/java/biz/paluch/redis/streamfeaturepoll/config/RedisConfiguration.java

    @Bean
    @ConditionalOnProperty("stream.poll-enabled")
    public StreamReceiver<String, ObjectRecord<String, VoteMessage>> streamReceiver(ReactiveRedisConnectionFactory factory) {

        return StreamReceiver.create(factory, StreamReceiver.StreamReceiverOptions.builder().targetType(VoteMessage.class).build());
    }

}
