package edu.pasudo123.querydsl.app.team.repository.impl;

import edu.pasudo123.querydsl.app.team.model.Team;
import edu.pasudo123.querydsl.app.team.repository.TeamCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Created by pasudo123 on 2019-09-07
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class TeamCusomRepositoryImpl extends QuerydslRepositorySupport implements TeamCustomRepository {

    public TeamCusomRepositoryImpl() {

        super(Team.class);
    }
}
