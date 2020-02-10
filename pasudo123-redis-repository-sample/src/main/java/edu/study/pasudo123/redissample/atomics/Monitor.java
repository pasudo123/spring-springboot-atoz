package edu.study.pasudo123.redissample.atomics;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@RedisHash("log-monitor")
@ToString
public class Monitor {

    @Id
    private String id;

    @Indexed
    private Long index;

    @Indexed
    private String referenceId;

    @Indexed
    private LocalDateTime registerDate;

    private String monitorData;

    public Monitor(){}

    public Monitor(String id, Long index, String referenceId, LocalDateTime registerDate, String monitorData) {
        this.id = id;
        this.index = index;
        this.referenceId = referenceId;
        this.registerDate = registerDate;
        this.monitorData = monitorData;
    }

    public Monitor(final String referenceId, final LocalDateTime registerDate, final String monitorData) {
        this.index = MonitorIndexCounter.getIndex();
        this.referenceId = referenceId;
        this.registerDate = registerDate;
        this.monitorData = monitorData;
    }
}
