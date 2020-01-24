package edu.pasudo123.querydsl.app.member.repository;

import edu.pasudo123.querydsl.app.member.model.Member;
import edu.pasudo123.querydsl.app.member.model.MemberPk;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pasudo123 on 2019-09-05
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface MemberRepository extends JpaRepository<Member, MemberPk>, MemberCustomRepository {
}
