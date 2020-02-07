package edu.study.pasudo123.redissample.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@RedisHash("teacher")
@ToString
public final class Teacher {

    @Id
    private String id;

    @Indexed
    private String name;

    @Indexed
    private LocalDateTime registerDate;

    private Teacher(String name) {
        this.name = name;
        this.registerDate = LocalDateTime.now();
    }

    public static Teacher create(final String name){
        return new Teacher(name);
    }
}
