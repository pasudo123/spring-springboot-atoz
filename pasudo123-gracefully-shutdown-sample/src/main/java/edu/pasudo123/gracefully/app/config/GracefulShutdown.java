package edu.pasudo123.gracefully.app.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIMEOUT = 30;
    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.connector.pause();
        final Executor executor = this.connector.getProtocolHandler().getExecutor();

        if(executor instanceof ThreadPoolTaskExecutor){
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();

                // `TIMEOUT 만큼의 시간 대기하여 모든 작업이 중지되었는지를 파악`
                if(threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
                    return;
                }

                log.warn("Tomcat thread pool did not shut down gracefully within 30 Seconds. Proceeding with forceful shutdown");
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
