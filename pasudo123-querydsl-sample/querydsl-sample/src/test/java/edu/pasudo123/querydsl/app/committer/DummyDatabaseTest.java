package edu.pasudo123.querydsl.app.committer;

import edu.pasudo123.querydsl.app.league.model.League;
import edu.pasudo123.querydsl.app.league.repository.LeagueRepository;
import edu.pasudo123.querydsl.app.member.model.Member;
import edu.pasudo123.querydsl.app.member.repository.MemberRepository;
import edu.pasudo123.querydsl.app.team.model.Team;
import edu.pasudo123.querydsl.app.team.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-09-19
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Transactional
public class DummyDatabaseTest {

    @Autowired private LeagueRepository leagueRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private MemberRepository memberRepository;

    @Test
    @Commit
    public void 리그_테이블에_데이터를_삽입한다() {

        leagueRepository.save(League.builder().name("라리가").desc("스페인 리그").build());
        leagueRepository.save(League.builder().name("프리미어리그").desc("잉글랜드 리그").build());
        leagueRepository.save(League.builder().name("분데스리가").desc("독일 리그").build());
        leagueRepository.save(League.builder().name("세리에 A").desc("이탈리아 리그").build());
        leagueRepository.save(League.builder().name("리그 1").desc("프랑스 리그").build());
        leagueRepository.save(League.builder().name("에리디비시").desc("네덜란드 리그").build());
        leagueRepository.save(League.builder().name("K 리그").desc("대한민국 리그").build());

    }

    @Test
    @Rollback(false)
    public void 팀_테이블에_데이터를_삽입한다() {

        League spainLeague = leagueRepository.findByName("라리가").get();
        teamRepository.save(Team.builder().league(spainLeague).name("바르셀로나").description("메시를 주축으로 이루는 팀").build());
        teamRepository.save(Team.builder().league(spainLeague).name("레알마드리드").description("예전에 스타군단으로 알고있는 팀").build());
        teamRepository.save(Team.builder().league(spainLeague).name("발렌시아").description("이강인 축구선수가 소속된 팀").build());

        League engLeague = leagueRepository.findByName("프리미어리그").get();
        teamRepository.save(Team.builder().league(engLeague).name("리버풀").description("클롭감독 지휘아래 18/19 챔스 우승 팀").build());
        teamRepository.save(Team.builder().league(engLeague).name("토트넘").description("18/19 챔스 준우승 팀, 손흥민 선수가 소속된 팀").build());
        teamRepository.save(Team.builder().league(engLeague).name("맨체스터 Utd").description("박지성 선수가 한 때 뛰었던 팀").build());

    }

    @Test
    @Commit
    public void 멤버_테이블에_데이터를_삽입한다() {

        Team fcBarcelona = teamRepository.findByName("바르셀로나").get();
        memberRepository.save(Member.builder().team(fcBarcelona).name("리오넬 메시").description("현존하는 바르셀로나의 전설적인 선수").build());
        memberRepository.save(Member.builder().team(fcBarcelona).name("앙투안 그리즈만").description("프랑스 선수이며, 세컨드 스트라이커").build());
        memberRepository.save(Member.builder().team(fcBarcelona).name("프랑키 데용").description("네덜란드의 신성").build());

        Team fcRealMadrid = teamRepository.findByName("레알마드리드").get();
        memberRepository.save(Member.builder().team(fcRealMadrid).name("에덴 아자르").description("첼시에서 이적한 선수, 지금 부상때매 못 뛰고 있음").build());
        memberRepository.save(Member.builder().team(fcRealMadrid).name("가레스 베일").description("전 축구선수, 현 골프선수").build());
        memberRepository.save(Member.builder().team(fcRealMadrid).name("카림 벤제마").description("프랑스 국대며 현 레알마드리드 소속").build());

        Team fcTottenham = teamRepository.findByName("토트넘").get();
        memberRepository.save(Member.builder().team(fcTottenham).name("손흥민").description("대한민국 국대며, 탑 윙어").build());
        memberRepository.save(Member.builder().team(fcTottenham).name("무사 시소코").description("토트넘 미드필더").build());
        memberRepository.save(Member.builder().team(fcTottenham).name("위고 요리스").description("토트넘 골키퍼").build());

        Team fcLiverpool = teamRepository.findByName("리버풀").get();
        memberRepository.save(Member.builder().team(fcLiverpool).name("모하메드 살라").description("이집트 메시라고 불림").build());
        memberRepository.save(Member.builder().team(fcLiverpool).name("버질 반 데이크").description("리버풀의 벽").build());
        memberRepository.save(Member.builder().team(fcLiverpool).name("알리송 베케르").description("리버풀 골키퍼").build());

    }
}
