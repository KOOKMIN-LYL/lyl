package com.kookmin.lyl.module.member.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    public final MemberRepository memberRepository;

    // Autowired 로 MemberService 가 생성이 될 때에 스프링이 Service라고 컨테이너에 등록을 하고
    // MemberRepository를 Service에 넣어준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 회원가입
    public void join (Member member){
        // 일단 첫 번재, 회원가입을 할 건데 id가 중복이면 안되잖아. 밑에 구현해 놓을 것임
        validateDuplicateMember(member);
        // 저장
        memberRepository.save(member);
    }
    // 중복 검사 -> 동명이인은 있을 수 있으나, id 는 유일해야 한다.
    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 아이디 입니다");
                });
    }
    // 회원 전체를 찾기
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }

}
