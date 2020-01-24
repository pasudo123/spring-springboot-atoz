package edu.study.pasudo123.app.user.repository;

import edu.study.pasudo123.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
