package com.kookmin.lyl.module.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="SHOP")
public class Shop {
    @Id @GeneratedValue
    @Column(name="SHOP_NUMBER")
    private Long shopNumber;

    @Column(name = "ID")
    private String id;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BUSINESS_NUMBER")
    private String businessNumber;
}
