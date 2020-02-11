package edu.study.pasudo123.redissample.service;

import edu.study.pasudo123.redissample.config.TestRedisConfiguration;
import edu.study.pasudo123.redissample.pojo.RedisEntity;
import edu.study.pasudo123.redissample.repository.RedisEntityRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class RedisRepositoryTest {

    @Autowired
    private RedisEntityRepository redisEntityRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @ParameterizedTest
    @MethodSource("provideRedisEntityList")
    void save_Find_Test(final List<RedisEntity> paramsList) {

        for(RedisEntity entity : paramsList) {
            redisEntityRepository.save(entity);
        }

        final List<RedisEntity> list = redisEntityRepository.findByReferenceId("ref1");

        assertThat(list.size()).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("provideRedisEntityList")
    void save_Remove_Test(final List<RedisEntity> paramsList) {

        for(RedisEntity entity : paramsList) {
            redisEntityRepository.save(entity);
        }

        // ref1 리스트
        final List<RedisEntity> list = redisEntityRepository.findByReferenceId("ref1");

        assertThat(list.size()).isEqualTo(10);

        redisEntityRepository.deleteAll(list);

        final List<RedisEntity> emptyList = redisEntityRepository.findByReferenceId("ref1");

        assertThat(emptyList.size()).isEqualTo(0);

        // ref2 리스트
        final List<RedisEntity> anotherList = redisEntityRepository.findByReferenceId("ref2");

        assertThat(anotherList.size()).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("provideRedisEntityList")
    @SuppressWarnings("unchecked")
    void save_Range_Find_test(final List<RedisEntity> paramsList) {

        for(RedisEntity entity : paramsList) {
            redisEntityRepository.save(entity);
        }

        Object object = redisTemplate.opsForHash().entries("log-monitor");

        System.out.println("==");
    }

    static Stream<List<RedisEntity>> provideRedisEntityList() {

        final List<RedisEntity> entityList = new ArrayList<>();

        for(int i = 1; i <= 100; i++) {
            final RedisEntity entity = RedisEntity
                    .builder()
                    .referenceId("ref" + i % 10)
                    .number(i)
                    .registerDate(LocalDateTime.now())
                    .data("{}")
                    .build();

            entityList.add(entity);
        }

        return Stream.of(entityList);
    }
}
