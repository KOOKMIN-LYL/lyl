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

    // 상품의 ID와 겹칠 수 있으므로 구분을 위해 MEMBER_ 삽입
    @Column(name="MEMBER_ID")
    private String id;

    // 상품의 이름에 NAME 이 사용될 수 있으므로 구분을 위해 MEMBER_ 삽입
    @Column(name="MEMBER_NAME")
    private String name;

    // 사용자의 주소와 주문 주소가 다를 수 있으니, MEMBER_ 을 붙여놓겠습니다
    @Column(name="MEMBER_ADDRESS")
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
    @Column(name="MEMBER_STATUS")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="MEMBER_TYPE")
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