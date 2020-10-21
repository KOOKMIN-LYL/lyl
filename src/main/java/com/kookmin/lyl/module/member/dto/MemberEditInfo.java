package com.kookmin.lyl.module.member.dto;

import lombok.Data;

@Data
public class MemberEditInfo {
    private Long usn;
    private String address;
    private String phone;
    private String email;
}
