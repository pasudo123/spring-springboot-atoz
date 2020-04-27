package edu.pasudo123.gracefully.app.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gracefully 하게 스프링부트가 종료가 되지 않는다. 뭔가 문제가 있는듯. 확인이 필요함
 */
@Configuration
public class GracefulConfiguration {

    @Bean
    public GracefulShutdown gracefulShutdown(){
        return new GracefulShutdown();
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final GracefulShutdown gracefulShutdown){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(gracefulShutdown);
        return factory;
    }
}
