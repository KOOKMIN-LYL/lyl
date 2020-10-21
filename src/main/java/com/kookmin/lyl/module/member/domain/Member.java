package com.kookmin.lyl.module.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="Member")
public class Member {
    @Id @GeneratedValue
    @Column(name="USN")
    private Long usn;

    @Column(name="MEMBER_ID", unique = true)
    private String memberId;

    @Column(name="MEMBER_NAME")
    private String name;

    @Column(name="MEMBER_ADDRESS")
    private String address;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="MEMBER_STATUS")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="MEMBER_TYPE")
    private MemberType memberType;

    @Builder
    public Member(String name, String memberId, String password, String address, String phone, String email) {
        this.name = name;
        this.memberId = memberId;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.status = MemberStatus.ACTIVE;
        this.memberType = MemberType.USER;
    }

    public void editInfo(String address, String phone, String email) {
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public void editName(String name) {
        this.name = name;
    }
    public void editPassword(String password) { this.password = password; }
}