package com.kookmin.lyl.module.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="Member")
public class Member {
    private final static Long DEFAULT_SORT_ORDER = 0L;

    @Id @GeneratedValue
    @Column(name="USN")
    private Long usn;

    @Column(name="ID")
    private String id;

    @Column(name="SORT_ORDER")
    private Long sortOrder;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;


//    @Column(name="zipCode")
//    private int zipCode;
//    짚코드는 빼자

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="status")
    private Enum status;

    @Column(name="memberType")
    private Enum memberType;



    @Builder
    public Member(String name) {
        this.name = name;
        this.sortOrder = DEFAULT_SORT_ORDER;
    }

    public void swapSortOrder(Member targetMember) {
        long originSortOrder = this.sortOrder;
        this.sortOrder = targetMember.sortOrder;
        targetMember.sortOrder = originSortOrder;
    }

    public void editName(String name) {

        this.name = name;
    }
}