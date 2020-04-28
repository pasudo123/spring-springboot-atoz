//package edu.pasudo123.gracefully.app.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.connector.Connector;
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextClosedEvent;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
//
//    private static final int TIMEOUT = 30;
//    private volatile Connector connector;
//
//    @Override
//    public void customize(Connector connector) {
//        this.connector = connector;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextClosedEvent event) {
//        this.connector.pause();
//        final Executor executor = this.connector.getProtocolHandler().getExecutor();
//
//        log.debug("종료하는 이벤트가 발생");
//
//        if(executor instanceof ThreadPoolTaskExecutor){
//            try {
//                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
//                threadPoolExecutor.shutdown();
//
//                log.debug("shutdown 수행.");
//
//                // `TIMEOUT 만큼의 시간 대기하여 모든 작업이 중지되었는지를 파악`
//                if(!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
//                    log.warn("Tomcat thread pool did not shut down gracefully within 30 Seconds. Proceeding with forceful shutdown");
//
//                    log.debug("지금 바로 종료 수행.");
//                    threadPoolExecutor.shutdownNow();
//
//                    if(!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
//                        log.error("Tomcat thread pool did not terminate");
//                    }
//                }
//
//                log.debug("끝 ==");
//
//            } catch(InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//}
