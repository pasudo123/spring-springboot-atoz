package edu.pasudo123.querydsl.app.league.repository.impl;

import edu.pasudo123.querydsl.app.league.model.League;
import edu.pasudo123.querydsl.app.league.repository.LeagueCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Created by pasudo123 on 2019-09-07
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class LeagueCustomRepositoryImpl extends QuerydslRepositorySupport implements LeagueCustomRepository {

    public LeagueCustomRepositoryImpl() {
        super(League.class);
    }

}
