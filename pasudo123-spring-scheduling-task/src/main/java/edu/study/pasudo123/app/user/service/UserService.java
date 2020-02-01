package edu.study.pasudo123.app.user.service;

import edu.study.pasudo123.app.exception.MemoryTableInsertException;
import edu.study.pasudo123.app.user.model.User;
import edu.study.pasudo123.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String saveIfPossibleElseDeleteAndSave(final String name) throws DataAccessException {

        final User user = User.builder()
                .age(0)
                .name(name)
                .gender(User.Gender.MAN)
                .build();

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
