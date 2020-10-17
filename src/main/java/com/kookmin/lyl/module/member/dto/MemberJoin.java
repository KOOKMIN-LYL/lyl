package com.kookmin.lyl.module.member.dto;

import lombok.Data;

@Data
public class MemberJoin {
    // 생성부
    private String memberName;
    private String memberId;
    private String password;
    private String address;
    private String phone;
    private String email;
}
