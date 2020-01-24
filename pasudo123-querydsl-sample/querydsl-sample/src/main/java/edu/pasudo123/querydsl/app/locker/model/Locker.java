package edu.pasudo123.querydsl.app.locker.model;

import lombok.Builder;

import javax.persistence.*;

/**
 * Created by pasudo123 on 2019-09-09
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Entity
@Table(name = "locker")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "locker_name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "locker_desc", columnDefinition = "VARCHAR(100)")
    private String description;

    @Builder
    public Locker(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
