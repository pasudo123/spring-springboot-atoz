package edu.study.pasudo123.app;

import edu.study.pasudo123.app.member.model.Gender;
import edu.study.pasudo123.app.member.model.Member;
import edu.study.pasudo123.app.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
@Slf4j
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MemberRepository memberRepository) {

        return args -> {

            for(int i = 0; i < 5; i++){

                final Member member = Member.builder()
                        .name("사람" + i)
                        .age(new Random().nextInt(11))
                        .gender((i % 2 == 0) ? Gender.MAN : Gender.WOMAN)
                        .build();

                memberRepository.save(member);
            }

        };
    }
}
