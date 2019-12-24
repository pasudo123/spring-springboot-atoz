package edu.memory.pasudo123.app.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-11-09
 * Email: oraedoa@gmail.com
 **/
@Service
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    @Override
    public void onMessage(final Message message, final byte[] pattern) {

        messageList.add(message.toString());

        log.info("Pattern Received : {}", new String(pattern));
        log.info("Message Received : {}", message.getBody());
    }
}