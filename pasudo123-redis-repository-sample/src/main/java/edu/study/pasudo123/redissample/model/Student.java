package edu.study.pasudo123.redissample.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@RedisHash("student")
@ToString
public final class Student {

    public enum Gender {
        MALE, FEMALE
    }

    @Id
    private String id;

    @Indexed
    private String name;

    private Gender gender;

    private int grade;

    @Indexed
    private LocalDateTime registerDate;

    private Student(String name, Gender gender, int grade) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.registerDate = LocalDateTime.now();
    }

    public static Student create(final String name, Gender gender, int grade) {
        return new Student(name, gender, grade);
    }
}
