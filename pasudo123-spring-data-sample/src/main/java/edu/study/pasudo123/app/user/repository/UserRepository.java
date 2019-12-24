package edu.study.pasudo123.app.user.repository;

import edu.study.pasudo123.app.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> save(User user);
}
