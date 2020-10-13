package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;

@Data
public class MemberEditInfo {
    // 기본 정보만 변경
    private String memberName;
    private String address;
    private String phone;
    private String email;

    // TODO:: MemberService 랑 이렇게 겹치게 하는게 맞다고? 으으으으
    public MemberEditInfo(Member member) {
        this.memberName = memberName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
