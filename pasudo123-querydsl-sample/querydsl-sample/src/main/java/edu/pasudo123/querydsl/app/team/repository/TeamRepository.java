package edu.pasudo123.querydsl.app.team.repository;

import edu.pasudo123.querydsl.app.team.model.Team;
import edu.pasudo123.querydsl.app.team.model.TeamPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by pasudo123 on 2019-09-05
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface TeamRepository extends JpaRepository<Team, TeamPk>, TeamCustomRepository {

    Optional<Team> findByName(String name);

}
