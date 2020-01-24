package edu.study.pasudo123.app.member.repository;

import edu.study.pasudo123.app.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
