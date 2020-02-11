package edu.study.pasudo123.redissample.service;

import edu.study.pasudo123.redissample.config.TestRedisConfiguration;
import edu.study.pasudo123.redissample.pojo.RedisDummy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
    void redisTemplate_Set_Add_Pop_Test(List<RedisDummy> redisDummies){

        // save
        redisTemplate.opsForSet().add(DUMMY_KEY, redisDummies);

        // get
        /** pop : pop 은 해당 키 값에 대한 밸류가 사라짐 **/
        final Object dummyList = redisTemplate.opsForSet().pop(DUMMY_KEY);

        final List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) dummyList;

        assert list != null;
        for(LinkedHashMap<String, Object> element : list) {
            for(String key : element.keySet()){
                System.out.println(key + " : " + element.get(key));
            }
            System.out.println("=====");
        }

        // pop() 는 레디스 밸류가 사라짐
        assertThat(redisTemplate.opsForSet().size(DUMMY_KEY)).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_Set_Add_Members_Test(List<RedisDummy> redisDummies) {

        // save
        redisTemplate.opsForSet().add(DUMMY_KEY, redisDummies);

        // get
        /** members : members 는 해당 키 값에 대한 밸류가 사라지지 않음 **/
        final Set<Object> dummySet = redisTemplate.opsForSet().members(DUMMY_KEY);

        assert dummySet != null;
        for(Object set : dummySet) {

            List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) set;

            for(LinkedHashMap<String, Object> element : list) {
                for(String key : element.keySet()){
                    System.out.println(key + " : " + element.get(key));
                }
                System.out.println("=====");
            }
        }

        // members() 는 레디스 밸류가 사라지지 않음
        assertThat(redisTemplate.opsForSet().size(DUMMY_KEY)).isEqualTo(1);
    }

    @Test
    @SuppressWarnings("unchecked")
    void redisTemplate_Set_Add_Add_Test() {

        // given
        final List<RedisDummy> firstDummyList = Stream.of(
                new RedisDummy("dummy1", 1),
                new RedisDummy("dummy2", 2),
                new RedisDummy("dummy3", 3),
                new RedisDummy("dummy4", 4),
                new RedisDummy("dummy5", 5)
        ).collect(Collectors.toList());

        final List<RedisDummy> secondDummyList = Stream.of(
                new RedisDummy("dummy6", 6),
                new RedisDummy("dummy7", 7),
                new RedisDummy("dummy8", 8),
                new RedisDummy("dummy9", 9),
                new RedisDummy("dummy10", 10)
        ).collect(Collectors.toList());

        // when : 덮어쓰기.
        redisTemplate.opsForSet().add(DUMMY_KEY, firstDummyList);

        redisTemplate.opsForSet().add(DUMMY_KEY, secondDummyList);

        final Object dummyList = redisTemplate.opsForSet().pop(DUMMY_KEY);

        final List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) dummyList;

        // then
        /** 동일 키 값에 대해서 덮어씌어져서 저장. **/
        assert list != null;
        assertThat(list.size()).isEqualTo(5);
    }


    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_Expire_Set_AddTest(List<RedisDummy> redisDummies) throws InterruptedException {

        // given
        redisTemplate.opsForSet().add(DUMMY_KEY, redisDummies);
        redisTemplate.expire(DUMMY_KEY, 3, TimeUnit.SECONDS);

        Thread.sleep(3000);

        // when
        final Long size = redisTemplate.opsForSet().size(DUMMY_KEY);

        // then
        assertThat(size).isEqualTo(0);
    }

    static Stream<List<RedisDummy>> provideRedisDummyList() {

        final List<RedisDummy> dummyList = Stream.of(
            new RedisDummy("dummy1", 1),
            new RedisDummy("dummy2", 2),
            new RedisDummy("dummy3", 3),
            new RedisDummy("dummy4", 4),
            new RedisDummy("dummy5", 5)
        ).collect(Collectors.toList());

        return Stream.of(dummyList);
    }
}
