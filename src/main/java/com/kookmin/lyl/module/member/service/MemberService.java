package com.kookmin.lyl.module.member.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.domain.MemberType;
import com.kookmin.lyl.module.member.dto.*;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.*;
public interface MemberService {

    public Long join (MemberJoin memberJoin);   // 회원가입 (join)
    public void changeMemberType(MemberDetails memberDetails);  // 회원 타입 변경
    public void validateDuplicateMember(String id,  String email, MemberJoin memberJoin);   // id, email로 중복 검사하기
    public List<Member> findMembers();          // 회원 전체를 조회
    public Member findByEmail(String email);    // email 로 한 사람 검색
    public Member findByMemberId(String id);    // id로 한 사람 검색
    public List<Member> findByMemberName(String name);  // 이름으로 멤버 검색
    public void editProfile( String id, String password, MemberPasswordCheck memberPasswordCheck, MemberEditInfo memberEditInfo);   // 개인정보 변경하기
}
