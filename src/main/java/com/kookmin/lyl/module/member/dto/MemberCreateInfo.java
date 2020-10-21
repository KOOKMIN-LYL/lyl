package com.kookmin.lyl.module.member.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class MemberCreateInfo {
    private String id;
    private String name;
    private String address;
    private String password;
    private String phone;
    private String email;
}
