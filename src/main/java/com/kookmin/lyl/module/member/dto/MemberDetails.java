package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;

import javax.persistence.*;

@Data
public class MemberDetails {
    private Long usn;
    private String memberId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String status;
    private String memberType;

    public MemberDetails(Member member) {
        this.usn = member.getUsn();
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.address = member.getAddress();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.status = member.getStatus().toString();
        this.memberType = member.getMemberType().toString();
    }
}
