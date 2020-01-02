package edu.study.pasudo123.app.user.repository;


import edu.study.pasudo123.app.user.model.User;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    /**
     * - Application 은 EntityManagerFactory 1 개만 생성 가능.
     * - EntityManagerFactory 를 통해 EntityManager 생성 가능.
     * <p>
     * - EntityManagerFactory : 스레드 세이프 O
     * - EntityManager : 스레드 세이프 X
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> save(User user) throws DataAccessException {


        return Optional.empty();
    }
}
