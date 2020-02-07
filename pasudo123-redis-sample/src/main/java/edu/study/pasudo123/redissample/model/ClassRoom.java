package edu.study.pasudo123.redissample.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@RedisHash("classroom")
@ToString
public class ClassRoom {

    @Id
    private String id;

    @Reference
    private final Teacher teacher;

    @Reference
    private final List<Student> studentList;


    private ClassRoom(Teacher teacher, List<Student> studentList) {
        this.teacher = teacher;
        this.studentList = studentList;
    }

    public static ClassRoom create(final Teacher teacher, final List<Student> studentList) {
        return new ClassRoom(teacher, studentList);
    }
}
