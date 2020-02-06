package edu.study.pasudo123.redissample.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisWriteReadConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.url}")
    private String url;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }

    @Bean
    public RedisClient redisClient(){

        return RedisClient.create(RedisURI.create(url));
    }
}
