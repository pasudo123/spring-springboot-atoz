package edu.study.pasudo123.app.service;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {
        TaskFixedScheduler.class
})
public class TaskFixedSchedulerTest {

    @SpyBean
    private TaskFixedScheduler scheduler;

    @Test
    public void reportCurrentTime() {
        await().atMost(Duration.ONE_MINUTE).untilAsserted(() -> {
           verify(scheduler, atLeast(2)).reportCurrentTimeByRate();
        });
    }
}
