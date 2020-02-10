package edu.study.pasudo123.redisstream.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stream")
@Setter
@Getter
public class PollConfiguration {

    private boolean pollEnabled;

}
