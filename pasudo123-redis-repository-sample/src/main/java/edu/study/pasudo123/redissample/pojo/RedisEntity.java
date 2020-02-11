package edu.study.pasudo123.redissample.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@RedisHash("log-monitor")
@ToString
public class RedisEntity {

    @Id
    private String id;

    @Indexed
    private String referenceId;

    private Integer number;

    private LocalDateTime registerDate;

    private String data;

    @Builder
    public RedisEntity(final String referenceId,
                       final Integer number,
                       final LocalDateTime registerDate,
                       final String data) {

        this.referenceId = referenceId;
        this.number = number;
        this.registerDate = registerDate;
        this.data = data;
    }
}
