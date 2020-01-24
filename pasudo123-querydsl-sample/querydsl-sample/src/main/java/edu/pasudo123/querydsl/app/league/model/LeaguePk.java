package edu.pasudo123.querydsl.app.league.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by pasudo123 on 2019-09-07
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LeaguePk implements Serializable {

    private String leagueUid;

    private String name;

}
