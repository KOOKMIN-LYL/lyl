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
    // usn은 자동생성으로
    @Id @GeneratedValue
    @Column(name="USN")
    private Long usn;

    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PASSWORD")
    private String password;

//    @Column(name="zipCode")
//    private int zipCode;
//    짚코드는 빼자

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Builder
    public Member(String name, String id, String password, String address, String phone, String email) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.status = MemberStatus.ACTIVE;
        this.memberType = MemberType.USER;
    }

    public void editName(String name) {
        this.name = name;
    }

}