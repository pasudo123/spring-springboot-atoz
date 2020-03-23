package edu.pasudo123.study.web.model;

public class User {

    private int id;
    private String name;
    private int age;
    private Gender gender;

    public enum Gender{
        MAN, WOMAN
    }

    public User(final int id, final String name, final int age, final Gender gender){
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public Integer getAge(){
        return age;
    }

    public String getGender(){
        return gender.name();
    }

    public boolean isEqualId(final int id){
        return this.id == id;
    }

    public boolean isEqualName(final String name){
        return this.name.equalsIgnoreCase(name);
    }

    public boolean isEqualAge(final int age){
        return this.age == age;
    }
}
