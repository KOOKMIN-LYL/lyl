package com.kookmin.lyl.module.member.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.domain.MemberType;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Autowired MemberRepository memberRepository;

    // 회원가입
    public void join (Member member){
        // 일단 첫 번재, 회원가입을 할 건데 id가 중복이면 안되잖아. 밑에 구현해 놓을 것임
        validateDuplicateMember(member);
        // 저장
        memberRepository.save(member);
    }

    // 회원의 타입을 변경하기
    public void changeMemberType(Member member){
        if(member.getMemberType().equals(MemberType.ADMIN)){
            member.setMemberType(MemberType.USER);
        }else {
            member.setMemberType(MemberType.ADMIN);
        }
    }

    // 중복 검사 -> 동명이인은 있을 수 있으나, id 는 유일해야 한다.
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByMemberId(member.getMemberId());   // MemberId 로 검사
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // 회원 전체를 찾기
    // TODO:: 로그인 로그아웃
    // 근데 이거는 서버 연결이 들어갔을 때 하는게 맞을 거 같은디
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // id로 검색
    public List<Member> findById(String id){
        return memberRepository.findByMemberId(id);
    }
    // 이름으로 검색
    public List<Member> findMember(String name) {return memberRepository.findByMemberName(name);}
    // email 로 검색
    public List<Member> findByEmail(String email) { return  memberRepository.findByEmail(email);}


    // 이거 처음에는 그대로 다 받아오고, 변경은 그걸 변경버튼 눌렀을 경우에만ㅇㅇ
    private void editProfile(Member member, String password, String memberName,  String address, String phone, String email){
        if(password.equals(member.getPassword())){
            if(!memberName.equals(member.getMemberName())){ member.setMemberName(memberName);}   // id 변경
            if(!address.equals(member.getAddress())){ member.setAddress(address); }     // 주소 변경
            if(!phone.equals(member.getPhone())){ member.setPhone(phone); }             // 번호 변경
            if(!email.equals(member.getEmail())){ member.setEmail(email); }             // 이메일 변경
        }
    }



}
