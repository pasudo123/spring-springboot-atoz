package edu.pasudo123.querydsl.app.model;

import edu.pasudo123.querydsl.app.member.model.Member;
import edu.pasudo123.querydsl.app.member.model.MemberPk;
import edu.pasudo123.querydsl.app.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import java.util.List;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Transactional
public class ProxyTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 멤버_테이블_조회_테스트() {

        Member member = memberRepository.findAll().get(0);
        entityManager.detach(member);
        entityManager.clear();

        System.out.println("================================ clear 수행 ================================");

        Member foundMember = memberRepository.findById(new MemberPk(member.getMemberUid(), member.getName())).get();

        System.out.println("11 ==> " + foundMember.getClass().getName());

        /** 프록시 클래스 확인 **/
        System.out.println("22 ==> " + foundMember.getTeam().getClass());

        /** 지연로딩 수행 (fetch.FetchType.LAZY) **/
        foundMember.getTeam().getName();
    }

    @Test
    public void N_플러스_1_조회_테스트() {

        /** SQL 로 반영 **/
        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();

        /**
         *
         * [ fetch.FetchType.EAGER 세팅되어 있는 상태 ]
         *
         * (1) SQL : SELECT * FROM Member
         * (2) SQL : SELECT * FROM Team
         * (3) SQL : SELEcT * FROM League
         */

    }
}