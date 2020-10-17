package com.kookmin.lyl.module.member.dto;

import lombok.Data;

@Data
public class MemberEditInfo {
    // 기본 정보만 변경
    private String memberId;
    private String password;
    private String memberName;
    private String address;
    private String phone;
    private String email;
}
