package edu.pasudo123.querydsl.app.league.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by pasudo123 on 2019-09-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@Entity
@Table(name = "league",
        indexes = {
                @Index(name = "league_name_idx", unique = true, columnList = "league_name")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(LeaguePk.class)
public class League {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(50)", nullable = false)
    private String leagueUid;

    @Id
    @Column(name = "league_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(name = "league_desc", columnDefinition = "VARCHAR(200)", nullable = true)
    private String desc;

    @Builder
    public League(String name, String desc) {
        this.leagueUid = UUID.randomUUID().toString().replace("-", "");
        this.name = name;
        this.desc = desc;
    }
}
