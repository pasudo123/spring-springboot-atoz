package edu.pasudo123.querydsl.app.member.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by pasudo123 on 2019-09-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPk implements Serializable {

    private String memberUid;

    private String name;

}
