package edu.study.pasudo123.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class TaskFixedScheduler {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    // 5초 주기 수행.
    @Scheduled(fixedRate = 5000L)
    public void reportCurrentTimeByRate() {
        log.info("fixedRate : {}", LocalDateTime.now().format(FORMAT));
    }

    // 이전 기준으로 10초 뒤 수행.
    @Scheduled(fixedDelay = 10000L)
    public void reportCurrentTimeByDelay() {
        log.info("fixedDelay : {}", LocalDateTime.now().format(FORMAT));
    }
}
