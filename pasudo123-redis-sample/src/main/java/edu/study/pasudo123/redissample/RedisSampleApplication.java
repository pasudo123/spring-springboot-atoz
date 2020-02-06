package edu.study.pasudo123.redissample;

import edu.study.pasudo123.redissample.model.Student;
import edu.study.pasudo123.redissample.repository.StudentRepository;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSampleApplication.class, args);
    }

    /**
     * https://github.com/lettuce-io/lettuce-core/blob/master/src/test/java/io/lettuce/examples/ReadWriteExample.java
     */
    @Bean
    public CommandLineRunner run(final RedisClient redisClient) {

        return args -> {

            final StatefulRedisConnection<String, String> connection = redisClient.connect();

            System.out.println("Connected to Redis");

            final RedisCommands<String, String> sync = connection.sync();

            sync.set("foo", "bar");
            String value = sync.get("foo");
            System.out.println(value);

            connection.close();
            redisClient.shutdown();
        };
    }

//    @Bean
//    public CommandLineRunner run(final StudentRepository studentRepository) {
//
//        return args -> {
//
//            final Student student = Student.builder()
//                    .id("s1")
//                    .gender(Student.Gender.MALE)
//                    .grade(1)
//                    .name("홍길동")
//                    .build();
//
//            // save
//            final Student savedStudent = studentRepository.save(student);
//
//            System.out.println("레포지토리에 [저장된] 학생 정보");
//            System.out.println(savedStudent);
//            System.out.println();
//
//            // find
//            final Student foundStudent = studentRepository.findById(savedStudent.getId()).get();
//
//            System.out.println("레포지토리에 [검색한] 학생 정보");
//            System.out.println(savedStudent);
//            System.out.println();
//
//
//            // delete
//            studentRepository.deleteById(foundStudent.getId());
//
//            final int count = (int) studentRepository.count();
//
//            System.out.println("레포지토리에 [검색한] 총 학생의 수");
//            System.out.println(count);
//            System.out.println();
//        };
//    }
}
