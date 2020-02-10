package edu.study.pasudo123.redissample.atomics.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorDto {

    private String id;

    private String index;

    private String referenceId;

    private LocalDateTime fromDate;

    private String monitorData;
}
