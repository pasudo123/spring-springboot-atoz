package edu.pasudo123.querydsl.app.member.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.pasudo123.querydsl.app.member.model.Member;
import edu.pasudo123.querydsl.app.member.model.QMember;
import edu.pasudo123.querydsl.app.member.repository.MemberCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Created by pasudo123 on 2019-09-07
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class MemberCustomRepositoryImpl extends QuerydslRepositorySupport implements MemberCustomRepository {

    private JPAQueryFactory jpaQueryFactory;

    public MemberCustomRepositoryImpl() {
        super(Member.class);
    }

    public Member findById(String id){

        QMember member = QMember.member;

        return null;
    }
}
