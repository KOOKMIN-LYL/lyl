package com.kookmin.lyl.module.member.repository;

import com.kookmin.lyl.module.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByMemberId(String memberId);
}