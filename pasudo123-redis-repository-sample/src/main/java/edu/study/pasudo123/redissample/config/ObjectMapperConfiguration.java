package edu.study.pasudo123.redissample.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

    // 레디스에서 LocalDateTime 역직렬화 시 문제점 해결 링크.
    // https://programmer.help/blogs/solve-jackson-2-deserialization-localdatetime-error.html

    @Bean
    public ObjectMapper objectMapper() {

        // LocalDateTime 역직렬화 해결.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }

}
