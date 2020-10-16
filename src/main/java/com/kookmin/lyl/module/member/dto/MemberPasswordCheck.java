package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;

@Data
public class MemberPasswordCheck {
    private String id;
    private String password;

    public MemberPasswordCheck(Member member){
        this.id = member.getMemberId();
        this.password = member.getPassword();
    }
}
