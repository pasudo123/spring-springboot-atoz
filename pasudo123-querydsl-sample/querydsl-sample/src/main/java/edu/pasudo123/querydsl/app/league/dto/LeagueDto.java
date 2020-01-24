package edu.pasudo123.querydsl.app.league.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by pasudo123 on 2019-09-07
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class LeagueDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class createRequest{
        private String name;
        private String desc;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class nameRequest{

        private String name;

    }

}
