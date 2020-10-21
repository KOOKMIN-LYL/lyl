package com.kookmin.lyl.module.member.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.dto.MemberCreateInfo;
import com.kookmin.lyl.module.member.dto.MemberEditInfo;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(MemberCreateInfo memberCreateInfo) {
        Member member = Member.builder()
                .memberId(memberCreateInfo.getId())
                .password(memberCreateInfo.getPassword())
                .name(memberCreateInfo.getName())
                .address(memberCreateInfo.getAddress())
                .email(memberCreateInfo.getEmail())
                .phone(memberCreateInfo.getPhone())
                .build();

        member = memberRepository.save(member);

        return member.getUsn();
    }

    public boolean validateDuplicateMember(String memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);

        if(member != null) return true;

        return false;
    }

    public void editMemberInfo(MemberEditInfo memberEditInfo) {
        Member member = memberRepository.findById(memberEditInfo.getUsn()).orElseThrow(EntityNotFoundException::new);

        member.editInfo(memberEditInfo.getAddress(),
                memberEditInfo.getPhone(),
                memberEditInfo.getEmail());
    }

    public void changePassword(Long usn, String newPassword) {
        Member member = memberRepository.findById(usn).orElseThrow(EntityNotFoundException::new);

        member.editPassword(newPassword);
    }


}
