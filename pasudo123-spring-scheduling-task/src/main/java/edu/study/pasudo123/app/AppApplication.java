package edu.study.pasudo123.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

//    @Bean
//    @Transactional
//    public CommandLineRunner runner(MemberRepository memberRepository, UserRepository userRepository) {
//
//        return args -> {

//            final int maxSize = 50000;
//
//            for(int i = 0; i < maxSize; i++){
//
//                final Member member = Member.builder()
//                        .name("멤버" + i)
//                        .age(new Random().nextInt(100))
//                        .gender((i % 2 == 0) ? Member.Gender.MAN : Member.Gender.WOMAN)
//                        .build();
//
//                memberRepository.save(member);
//            }
//
//            for(int i = 0; i < maxSize; i++){
//
//                final User user = User.builder()
//                        .name("유저" + i)
//                        .age(new Random().nextInt(100))
//                        .gender((i % 2 == 0) ? User.Gender.MAN : User.Gender.WOMAN)
//                        .build();
//
//                userRepository.save(user);
//            }
//        };
//    }
}
