package edu.study.pasudo123.app.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Dummy {

    private String name;
    private Integer hour;
    private Integer minute;
    private Integer randomNumber;

    @Builder
    public Dummy(String name,
                 Integer hour,
                 Integer minute,
                 Integer randomNumber) {

        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.randomNumber = randomNumber;
    }
}
