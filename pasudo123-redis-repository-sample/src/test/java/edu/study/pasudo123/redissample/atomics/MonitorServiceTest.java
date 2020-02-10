package edu.study.pasudo123.redissample.atomics;

import edu.study.pasudo123.redissample.atomics.pojo.MonitorDto;
import edu.study.pasudo123.redissample.config.TestRedisConfiguration;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        TestRedisConfiguration.class,
})
class MonitorServiceTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @BeforeEach
    void init() throws InterruptedException {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        AtomicInteger val = new AtomicInteger(1);

        final List<LocalDateTime> fromDateList = new ArrayList<>();
        fromDateList.add(LocalDateTime.of(2020,2,10, 10, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 11, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 12, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 13, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 14, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 15, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 16, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 17, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 18, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 19, 0, 0));
        fromDateList.add(LocalDateTime.of(2020,2,10, 20, 0, 0));

        // 모니터 데이터 삽입
        for(int i = 0 ; i < 10; i++) {
            final Monitor monitor = new Monitor(String.valueOf(i+1), fromDateList.get(i), "{}");
            redisTemplate.opsForList().leftPush("domainId" + i, monitor);
        }
    }

    @Test
    void getRangeMonitorTest() {

        System.out.println(redisTemplate.opsForList().leftPop("domainId0"));
        System.out.println(redisTemplate.opsForList().leftPop("domainId1"));
        System.out.println(redisTemplate.opsForList().leftPop("domainId2"));
        System.out.println(redisTemplate.opsForList().leftPop("domainId3"));
        System.out.println(redisTemplate.opsForList().leftPop("domainId4"));

//        final String referenceId = "1";
//        final LocalDateTime fromDate = LocalDateTime.of(2020,2,10,11,0,0);
    }
}
