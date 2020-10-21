package com.kookmin.lyl.module.member.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.dto.MemberCreateInfo;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(MemberCreateInfo memberCreateInfo) {
        Member member = Member.builder()
                .id(memberCreateInfo.getId())
                .password(memberCreateInfo.getPassword())
                .name(memberCreateInfo.getName())
                .address(memberCreateInfo.getAddress())
                .email(memberCreateInfo.getEmail())
                .phone(memberCreateInfo.getPhone())
                .build();

        member = memberRepository.save(member);

        return member.getUsn();
    }


}
