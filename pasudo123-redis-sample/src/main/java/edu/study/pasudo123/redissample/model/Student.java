package edu.study.pasudo123.redissample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("student")
@ToString
public class Student {

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;

    @Builder
    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }
}
