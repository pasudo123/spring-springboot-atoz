package edu.study.pasudo123.redissample.config;

import edu.study.pasudo123.redissample.model.Student;
import edu.study.pasudo123.redissample.model.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

import java.util.Arrays;

@Configuration
public class RedisKeySpaceConfiguration extends KeyspaceConfiguration {

    @Override
    protected Iterable<KeyspaceSettings> initialConfiguration() {

        /**
         * [키스페이스]
         * - 레디스 해시에서 실제 키로 사용되는 접두사 설정
         * - 기본적으로 getClass().getName() 으로 획득
         */
        final KeyspaceSettings settings1 = new KeyspaceSettings(Student.class, "student");
        final KeyspaceSettings settings2 = new KeyspaceSettings(Teacher.class, "teacher");

        return Arrays.asList(settings1, settings2);
    }
}
