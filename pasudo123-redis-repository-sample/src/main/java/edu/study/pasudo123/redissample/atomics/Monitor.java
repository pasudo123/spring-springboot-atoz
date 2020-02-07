package edu.study.pasudo123.redissample.atomics;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@RedisHash("monitor")
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

    public Monitor(final String referenceId, final String monitorData) {
        this.index = MonitorIndexCounter.getIndex();
        this.referenceId = referenceId;
        this.monitorData = monitorData;
    }
}
