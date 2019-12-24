package edu.memory.pasudo123.app.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-11-09
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisMessagePublisher implements MessagePublisher{

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    @Override
    public void publisher(final String message) {

        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
