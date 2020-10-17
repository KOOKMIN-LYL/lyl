package com.kookmin.lyl.module.member.repository;

import com.kookmin.lyl.module.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member member);
    List<Member> findByMemberId(String memberId);   // 여러 사람. 이런 아이디로 사람이 있는지 보려는 거였다. -> 다르게 바꿀 수 있을 거 같은데
    List<Member> findByMemberName(String memberName);
    Member findByEmail(String email);
    Member findOneByMemberId(String memberId);

}