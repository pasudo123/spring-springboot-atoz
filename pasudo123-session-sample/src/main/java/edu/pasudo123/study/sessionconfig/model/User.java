package edu.pasudo123.study.sessionconfig.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private String name;
    private int age;

    @Builder
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
