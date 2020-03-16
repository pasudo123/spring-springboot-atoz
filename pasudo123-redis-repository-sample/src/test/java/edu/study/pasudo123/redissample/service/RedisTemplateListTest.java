package edu.study.pasudo123.redissample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
class RedisTemplateListTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String DUMMY_KEY = "dummy";

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_List_PushTest(List<RedisDummy> redisDummies) {

        // save
        redisTemplate.opsForList().leftPushAll(DUMMY_KEY, redisDummies);
    }

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_List_LeftPopAndRightPopTest(List<RedisDummy> redisDummies) throws JsonProcessingException {

        // save
        redisTemplate.opsForList().leftPushAll(DUMMY_KEY, redisDummies);
        System.out.println("전체 사이즈 : " + redisTemplate.opsForList().size(DUMMY_KEY));

        String result = "";

        /** 오른쪽 pop **/
        result =  Objects.requireNonNull(redisTemplate.opsForList().rightPop(DUMMY_KEY)).toString();
        System.out.println(result);

        /** 왼쪽 pop **/
        result = Objects.requireNonNull(redisTemplate.opsForList().leftPop(DUMMY_KEY)).toString();
        System.out.println(result);

        final Long size = redisTemplate.opsForList().size(DUMMY_KEY);

        assertThat(size).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_List_ClearTest(List<RedisDummy> redisDummies) {

        // save
        redisTemplate.opsForList().leftPushAll(DUMMY_KEY, redisDummies);

        Long size = redisTemplate.opsForList().size(DUMMY_KEY);
        System.out.println("제거 이전 사이즈 : " + size);

        while(size > 0) {
            redisTemplate.opsForList().leftPop(DUMMY_KEY);
            size--;
        }

        System.out.println("제거 이후 사이즈 : " + size);
    }

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_List_While_EmptyTest(List<RedisDummy> paramRedisDummies) {

        redisTemplate.opsForList().leftPushAll(DUMMY_KEY, paramRedisDummies);

        final List<Object> list = redisTemplate.opsForList().range(DUMMY_KEY, 0, -1);

        assert list != null;
        final List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>) list.get(0);

        final List<RedisDummy> redisDummies = new ArrayList<>();

        for(LinkedHashMap<String, Object> map : mapList) {
            redisDummies.add(new RedisDummy(map));
        }

        for(RedisDummy dummy : redisDummies) {
            System.out.println(dummy);
        }

        final List<Object> newList = redisTemplate.opsForList().range(DUMMY_KEY, 0, -1);

        assert list != null;
        final List<LinkedHashMap<String, Object>> newMapList = (List<LinkedHashMap<String, Object>>) newList.get(0);

        assertThat(newMapList.size()).isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("provideRedisDummyList")
    @SuppressWarnings("unchecked")
    void redisTemplate_Push_One_Test(List<RedisDummy> paramsRedisDummies) {


        for(RedisDummy dummy : paramsRedisDummies) {
            redisTemplate.opsForList().leftPush(DUMMY_KEY, dummy);
        }

        final List<Object> list = redisTemplate.opsForList().range(DUMMY_KEY, 0, -1);

        ObjectMapper mapper = new ObjectMapper();
        List<RedisDummy> dummies = mapper.convertValue(list, new TypeReference<List<RedisDummy>>(){});

        System.out.println("=====");
    }

    static Stream<List<RedisDummy>> provideRedisDummyList() {

        final List<RedisDummy> dummyList = new ArrayList<>();

        dummyList.add(new RedisDummy("dummy1", 1));
        dummyList.add(new RedisDummy("dummy2", 2));
        dummyList.add(new RedisDummy("dummy3", 3));
        dummyList.add(new RedisDummy("dummy4", 4));
        dummyList.add(new RedisDummy("dummy5", 5));

        return Stream.of(dummyList);
    }
}
