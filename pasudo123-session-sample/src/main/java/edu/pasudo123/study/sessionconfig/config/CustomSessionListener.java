package edu.pasudo123.study.sessionconfig.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class CustomSessionListener extends HttpSessionEventPublisher {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent event){
        log.info("New Session Created.");
        counter.incrementAndGet();
        updateSessionCounter(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("Session Destroyed. Removing the Session from the counter.");
        counter.decrementAndGet();
        updateSessionCounter(event);
    }

    private void updateSessionCounter(HttpSessionEvent httpSessionEvent){
        httpSessionEvent.getSession().setAttribute("ActiveSession", counter.get());
        log.info("Total Active Session are {}", counter.get());
    }
}
