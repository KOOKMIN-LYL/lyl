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
                .build();

        member = memberRepository.save(member);
        return member.getUsn();
    }

    // 회원의 타입을 변경하기
    @Override
    public void changeMemberType(@NonNull MemberGetId memberGetId){
        List<Member> members = memberRepository.findByMemberId(memberGetId.getMemberId());
        for(Member member : members){
            if(member.getMemberType() == MemberType.ADMIN){
                member.setMemberType(MemberType.USER);
            }else {
                member.setMemberType(MemberType.ADMIN);
            }
        }
    }

    // id(이미 존재하는 id 인가) 와 email(이미 등록이 되었는가) 을 통해 이미 존재하는지에 대한 여부를 검사한다
    @Override
    public void validateDuplicateMember(@NonNull MemberValidateDuplicate memberValidateDuplicate, @NonNull MemberJoin memberJoin) {   // 중복 검사 -> 동명이인은 있을 수 있으나, id, email 는 유일해야 한다.
        if(memberRepository.findByMemberId(memberValidateDuplicate.getId()) != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }   // MemberId 로 검사
        if(memberRepository.findByEmail(memberValidateDuplicate.getEmail()) != null) {
            throw new IllegalStateException("이미 등록된 이메일입니다.");
        }   // email로 검사
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }    // 모든 멤버를 다져오는 방법

    @Override
    public List<Member> findByMemberName(@NonNull MemberFindByName memberFindByName) {
        return memberRepository.findByMemberName(memberFindByName.getName());
    }   // 이름으로 검색

    @Override
    public Member findByEmail(@NonNull MemberFindByEmail memberFindByEmail) {
        return  memberRepository.findByEmail(memberFindByEmail.getEmail());
    }   // email 로 검색

    @Override
    public Member findOneByMemberId(@NonNull MemberId memberId) {
        return memberRepository.findOneByMemberId(memberId.getMemberId());
    }   // 멤버 한 놈만 가져오기


    @Override
    public void editProfile(@NonNull MemberId memberId, @NonNull MemberEditInfo memberEditInfo){
        Member member = memberRepository.findOneByMemberId(memberId.getMemberId());
        member.setEmail(memberEditInfo.getEmail());
        member.setPhone(memberEditInfo.getPhone());
        member.setMemberName(memberEditInfo.getMemberName());
        member.setMemberId(memberEditInfo.getMemberId());
        member.setAddress(memberEditInfo.getAddress());
    }   // 자기 정보에 대한 수정 부분

    @Override
    public boolean checkPassword(@NonNull String password, @NonNull MemberPasswordCheck memberPasswordCheck) {
        Member member = memberRepository.findOneByMemberId(memberPasswordCheck.getId());
        return password.equals(member.getMemberId());
    }   // 비밀번호 확인 부분
}
