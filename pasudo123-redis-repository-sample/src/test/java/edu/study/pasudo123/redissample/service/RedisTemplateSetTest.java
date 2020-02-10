package edu.study.pasudo123.redissample.service;

import edu.study.pasudo123.redissample.config.TestRedisConfiguration;
import edu.study.pasudo123.redissample.pojo.RedisDummy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class RedisTemplateSetTest {

    /**
     * https://github.com/spring-projects/spring-data-redis/blob/master/src/test/java/org/springframework/data/redis/core/RedisTemplateTests.java
     */

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String DUMMY_KEY = "dummy";

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    public void redisTemplate_Set_AddTest(List<RedisDummy> redisDummies){

        // save
        redisTemplate.opsForSet().add(DUMMY_KEY, redisDummies);

        // get
        Set<RedisDummy> dummySet = redisTemplate.opsForSet().members(DUMMY_KEY);

        for(RedisDummy dummy : dummySet) {
            System.out.println(dummy);
        }
    }

    static Stream<List<RedisDummy>> provideRedisDummyList() {

        final List<RedisDummy> dummyList = Stream.of(
            new RedisDummy("dummy1", 1),
            new RedisDummy("dummy2", 2),
            new RedisDummy("dummy3", 3),
            new RedisDummy("dummy4", 4),
            new RedisDummy("dummy5", 5)
        ).collect(Collectors.toList());

        return Stream.of(
                dummyList
        );
    }
}
