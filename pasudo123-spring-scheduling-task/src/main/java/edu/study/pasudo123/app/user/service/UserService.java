package edu.study.pasudo123.app.user.service;

import edu.study.pasudo123.app.user.model.User;
import edu.study.pasudo123.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String saveIfPossibleElseDeleteAndSave(final String name) throws Exception {

        final User user = User.builder()
                .age(0)
                .name(name)
                .gender(User.Gender.MAN)
                .build();

        /**
         * save() 만 있는 경우 :
         * save() 를 try/catch 로 감싼경우 :
         */

        userRepository.save(user);
        return "SAVE";
    }

    @Transactional
    public String deleteAndSave(final String name) {

        final User user = User.builder()
                .age(0)
                .name(name)
                .gender(User.Gender.MAN)
                .build();

        userRepository.deleteOneLimit1();
        userRepository.save(user);

        return "DELETE AND SAVE";
    }
}
