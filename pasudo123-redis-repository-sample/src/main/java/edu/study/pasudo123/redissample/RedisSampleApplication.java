package edu.study.pasudo123.redissample;

import edu.study.pasudo123.redissample.model.ClassRoom;
import edu.study.pasudo123.redissample.model.Student;
import edu.study.pasudo123.redissample.model.Teacher;
import edu.study.pasudo123.redissample.repository.ClassRoomRepository;
import edu.study.pasudo123.redissample.repository.StudentRepository;
import edu.study.pasudo123.redissample.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedisSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSampleApplication.class, args);
    }

    // [secondary Index] : @Indexed
    // - redis 의 인덱스로 작용하고 redis cli 에서 keys 조회시 노출

    // [idx] : @Id
    // - redis 의 키로 작용하고 redis cli 에서 keys 조회시 노출



    @Bean
    public CommandLineRunner run(final TeacherRepository teacherRepository,
                                 final StudentRepository studentRepository,
                                 final ClassRoomRepository classRoomRepository) {

        return args -> {

            // student
            studentRepository.deleteAll();

            for(int i = 1; i <= 1; i++) {
                Student savedStudent = studentRepository.save(Student.create("HongGilDong", Student.Gender.MALE, 1));
                System.out.println(savedStudent);
            }

            // teacher
            teacherRepository.deleteAll();

            for(int i = 1; i <= 1; i++) {
                Teacher savedTeacher = teacherRepository.save(Teacher.create("ISunSin"));
                System.out.println(savedTeacher);
            }

            // classroom
            classRoomRepository.deleteAll();

            final List<Student> studentList = new ArrayList<>();
            for(int i = 1; i <= 2; i++) {
                final Student savedStudent = studentRepository.save(Student.create("park" + i, Student.Gender.MALE, 5));
                studentList.add(savedStudent);
            }

            final Teacher foundTeacher = teacherRepository.findByName("ISunSin");

            final ClassRoom savedClassRoom = classRoomRepository.save(ClassRoom.create(foundTeacher, studentList));

            System.out.println(savedClassRoom);
        };
    }

    /**
     * https://github.com/lettuce-io/lettuce-core/blob/master/src/test/java/io/lettuce/examples/ReadWriteExample.java
     */
}
