package edu.study.pasudo123.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {

        /**
         * 권장하지 않는 패스워드 인코더 : 더이상 {noop} 을 붙일 필요가 없다.
         * 패스워드 기본전략을 bcryt 로 변경하였음.
         * **/
        // return NoOpPasswordEncoder.getInstance();

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
