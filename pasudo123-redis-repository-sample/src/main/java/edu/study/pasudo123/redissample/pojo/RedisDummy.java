package edu.study.pasudo123.redissample.pojo;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RedisDummy {

    private String line;
    private int number;
    private LocalDateTime date;

    public RedisDummy(final String line,
                      final Integer number) {

        this.line = line;
        this.number = number;
        this.date = LocalDateTime.now();
    }
}
