package edu.pasudo123.gracefully.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@Slf4j
public class AppApplication {

    ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @PreDestroy
    public void destroy() {

        log.info("Triggered PreDestroy");

        while(executor.getActiveCount() > 0) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Complete all active threads.");
    }
}
