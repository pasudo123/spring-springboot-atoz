package edu.study.pasudo123.redissample.repository;

import edu.study.pasudo123.redissample.config.TestRedisConfiguration;
import edu.study.pasudo123.redissample.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("레디스 레파지토리 CRUD 테스트")
    void studentRepositoryTest() {

        studentRepository.deleteAll();

        // given
        final Student student = Student.create("honggildong", Student.Gender.MALE, 5);

        // save
        final Student savedStudent = studentRepository.save(student);

        assertAll("saveStudent",
                () -> assertEquals(student.getName(), savedStudent.getName()),
                () -> assertEquals(student.getGrade(), savedStudent.getGrade())
        );

        // find
        final Student foundStudent = studentRepository.findById(student.getId()).get();

        assertAll("foundStudent",
                () -> assertEquals(student.getName(), foundStudent.getName()),
                () -> assertEquals(student.getGrade(), foundStudent.getGrade())
        );

        // delete
        studentRepository.deleteById(foundStudent.getId());

        final int count = (int) studentRepository.count();

        assertThat(count).isEqualTo(0);
    }

}
