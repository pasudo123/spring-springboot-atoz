package edu.study.pasudo123.app.user.service;

import edu.study.pasudo123.app.user.model.User;
import edu.study.pasudo123.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional(TxType.SUPPORTS)
    public String saveIfPossibleElseDeleteAndSave(final String name){

        final User user = User.builder()
                .age(0)
                .name(name)
                .gender(User.Gender.MAN)
                .build();

        try{
            userRepository.save(user);
            return "SAVE";
        } catch(RuntimeException e){
            userRepository.deleteOneLimit1();
            userRepository.save(user);
            return "DELETE AND SAVE";
        }
    }
}
