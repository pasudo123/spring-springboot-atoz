package edu.study.pasudo123.app.user.repository;

import edu.study.pasudo123.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from users ORDER BY users.id ASC limit 1")
    void deleteOneLimit1();

}
