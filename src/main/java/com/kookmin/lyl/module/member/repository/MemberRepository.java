package com.kookmin.lyl.module.member.repository;

import com.kookmin.lyl.module.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}