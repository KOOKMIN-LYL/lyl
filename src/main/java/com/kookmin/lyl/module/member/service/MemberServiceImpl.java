package com.kookmin.lyl.module.member.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.domain.MemberType;
import com.kookmin.lyl.module.member.dto.*;
import com.kookmin.lyl.module.member.dto.MemberPasswordCheck;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 회원가입
    @Override
    public Long join (@NonNull MemberJoin memberJoin){
        Member member = Member.builder()
                .memberId(memberJoin.getMemberId())
                .memberName(memberJoin.getMemberName())
                .address((memberJoin.getAddress()))
                .email(memberJoin.getEmail())
                .phone(memberJoin.getPhone())
                .password(memberJoin.getPassword())
                .usn(memberJoin.getUsn())
                .build();

        member = memberRepository.save(member);
        return member.getUsn();
    }

    // 회원의 타입을 변경하기
    @Override
    public void changeMemberType(@NonNull MemberDetails memberDetails){
        if(memberDetails.getMemberType().equals(MemberType.ADMIN)){
            memberDetails.setMemberType(MemberType.USER);
        }else {
            memberDetails.setMemberType(MemberType.ADMIN);
        }
    }

    @Override
    public void validateDuplicateMember(@NonNull String id, @NonNull String email, @NonNull MemberJoin memberJoin) {   // 중복 검사 -> 동명이인은 있을 수 있으나, id, email 는 유일해야 한다.
        // MemberId 로 검사
        if(memberRepository.findByMemberId(id) != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }   // email로 검사
        if(memberRepository.findByEmail(email) != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }    // 모든 멤버를 다져오는 방법

    @Override
    public Member findByMemberId(@NonNull String id){
        return memberRepository.findByMemberId(id);
    }  // id로 검색

    @Override
    public List<Member> findByMemberName(@NonNull String name) {
        return memberRepository.findByMemberName(name);
    } // 이름으로 검색

    @Override
    public Member findByEmail(@NonNull String email) {
        return  memberRepository.findByEmail(email);
    } // email 로 검색

    @Override
    public void editProfile(@NonNull String id, @NonNull String password, @NonNull MemberPasswordCheck memberPasswordCheck, @NonNull MemberEditInfo memberEditInfo){

        Member member = memberRepository.findMemberByMemberId(id);

        if((memberPasswordCheck.getId().equals(id))&(memberPasswordCheck.getPassword().equals(password))){
            if(!memberEditInfo.getMemberName().equals(member.getMemberName())){ member.setMemberName(memberEditInfo.getMemberName());}   // id 변경
            if(!memberEditInfo.getAddress().equals(member.getAddress())){ member.setAddress(memberEditInfo.getAddress()); }     // 주소 변경
            if(!memberEditInfo.getPhone().equals(member.getPhone())){ member.setPhone(memberEditInfo.getPhone()); }             // 번호 변경
            if(!memberEditInfo.getEmail().equals(member.getEmail())){ member.setEmail(memberEditInfo.getEmail()); }             // 이메일 변경
        }
    }

}
