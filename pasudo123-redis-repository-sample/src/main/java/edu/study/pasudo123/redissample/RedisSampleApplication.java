package edu.study.pasudo123.redissample;

import edu.study.pasudo123.redissample.atomics.Monitor;
import edu.study.pasudo123.redissample.atomics.MonitorRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    public CommandLineRunner run(final StudentRepository studentRepository,
                                 final TeacherRepository teacherRepository,
                                 final ClassRoomRepository classRoomRepository,
                                 final MonitorRepository monitorRepository) {

        return args -> {
//            this.repositoryExecute(studentRepository, teacherRepository, classRoomRepository);
//            this.multiMonitorExecute(monitorRepository);
        };
    }
    /**
     * https://github.com/lettuce-io/lettuce-core/blob/master/src/test/java/io/lettuce/examples/ReadWriteExample.java
     */

    private void repositoryExecute(final StudentRepository studentRepository,
                                   final TeacherRepository teacherRepository,
                                   final ClassRoomRepository classRoomRepository) {

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
    }

    private void multiMonitorExecute(final MonitorRepository monitorRepository) throws InterruptedException {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        AtomicInteger val = new AtomicInteger(1);

        for(int i = 0; i < 100; i++) {
            final Monitor monitor = new Monitor(String.valueOf(i), LocalDateTime.now(), "{}");
            monitorRepository.save(monitor);
        }

        cachedThreadPool.shutdown();
        cachedThreadPool.awaitTermination(3000, TimeUnit.SECONDS);
    }
}
