package edu.study.pasudo123.app.user.repository;


import edu.study.pasudo123.app.user.model.User;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> save(User user) throws DataAccessException {



        return Optional.empty();
    }
}
