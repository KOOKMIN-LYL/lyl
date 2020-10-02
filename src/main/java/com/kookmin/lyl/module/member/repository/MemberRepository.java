package com.kookmin.lyl.module.member.repository;

import com.kookmin.lyl.module.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member member);
    Optional<Member> findById(String id);
    List<Member> findAll();
    List<Member> findByName(String name);
}