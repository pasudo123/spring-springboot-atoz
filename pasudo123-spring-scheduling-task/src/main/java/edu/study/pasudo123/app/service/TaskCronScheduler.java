package edu.study.pasudo123.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class TaskCronScheduler {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    // second, minute, hour, day, month, day
    // https://crontab.guru/
    // 매 시간의 30분마다 수행.
    @Scheduled(cron = "0 30 * * * *")
    public void scheduled(){
        log.debug("======================");
        log.debug("크론탭 수행 : {}", LocalDateTime.now().format(FORMAT));
        log.debug("======================");
    }
}
