package edu.study.pasudo123.redissample.atomics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonitorService {

    private final RedisTemplate redisTemplate;

    public List<Monitor> getRangeMonitor(final String referenceId, final LocalDateTime fromDate){

//        redisTemplate.opsForList().range()

        return null;
    }
}
