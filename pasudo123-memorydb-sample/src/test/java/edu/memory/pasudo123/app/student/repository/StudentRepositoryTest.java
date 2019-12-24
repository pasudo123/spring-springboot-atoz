package edu.memory.pasudo123.app.student.repository;

import edu.memory.pasudo123.app.config.RedisConfiguration;
import edu.memory.pasudo123.app.student.model.Student;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by pasudo123 on 2019-11-10
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RedisConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private static redis.embedded.RedisServer redisServer;

    private Student student = null;

    @BeforeClass
    public static void startRedisServer() throws IOException {

        System.out.println("=====> @BeforeClass");

        /** `maxmemory 128M` 을 제대로 써줘야 한다. 그렇지 않으면 에러 남. **/
        redisServer = new RedisServerBuilder().port(6379).setting("maxmemory 128M").build();
        redisServer.start();
    }

    @Before
    public void initStudent() {

        System.out.println("=====> @Before");

        this.student = Student.builder()
                .name("Park")
                .id("A001")
                .grade(1)
                .gender(Student.Gender.MALE)
                .build();
    }

    @Test
    public void Student_저장_및_반환_테스트() {

        System.out.println("Student_저장_및_반환_테스트 ===");

        final Student savedStudent = studentRepository.save(student);
        final Student foundStudent = studentRepository.findById(savedStudent.getId()).get();

        assertThat(foundStudent.getId(), is(savedStudent.getId()));
    }

    @Test
    public void Student_수정_및_반환_테스트() {

        System.out.println("Student_수정_및_반환_테스트 ===");

        final Student savedStudent = studentRepository.save(student);

        savedStudent.updateName("Kim");
        savedStudent.updateGrade(100);
        studentRepository.save(savedStudent);

        final Student foundStudent = studentRepository.findById(savedStudent.getId()).get();

        /** 아래는 에러가 나옴 : Persistence Context 와 느낌이 유사하다. **/
        assertThat(student.getName(), not(foundStudent.getName()));
        assertThat(student.getGrade(), not(foundStudent.getGrade()));

//        assertThat(savedStudent.getName(), is(foundStudent.getName()));
//        assertThat(savedStudent.getGrade(), is(foundStudent.getGrade()));
    }

    @Test
    public void Student_리스트_저장_및_반환_테스트() {

        final Student student1 = Student.builder().id("A001").grade(1).name("yang").gender(Student.Gender.FEMALE).build();
        final Student student2 = Student.builder().id("A002").grade(1).name("kim").gender(Student.Gender.MALE).build();
        final Student student3 = Student.builder().id("A003").grade(1).name("park").gender(Student.Gender.FEMALE).build();

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);

        assertThat(studentList.size(), is(3));
    }

    @Test
    public void Student_삭제_테스트() {

        final Student savedStudent = studentRepository.save(student);

        studentRepository.deleteById(savedStudent.getId());

        final Student foundStudent = studentRepository.findById(savedStudent.getId()).orElse(null);

        assertThat(foundStudent, is(nullValue()));
    }

    @AfterClass
    public static void stopRedisServer() throws IOException {

        System.out.println("=====> @AfterClass");

        if(redisServer == null) {
            return;
        }

        redisServer.stop();
    }
}
