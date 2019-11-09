package edu.memory.pasudo123.app;

import edu.memory.pasudo123.app.student.model.Student;
import edu.memory.pasudo123.app.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        /**
         * TODO 레디스 로컬 환경 테스트 만들기
         */

//        Student student = Student.builder()
//                .id("A001")
//                .gender(Student.Gender.FEMALE)
//                .name("Park")
//                .grade(1)
//                .build();
//
//        studentRepository.save(student);
    }
}
