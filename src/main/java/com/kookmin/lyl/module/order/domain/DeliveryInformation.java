package com.kookmin.lyl.module.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="ORDER_DELIVERY_INFORMATION")
public class DeliveryInformation {
    @Id @GeneratedValue
    @Column(name = "ORDER_DELIVERY_INFORMATION_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Builder
    public DeliveryInformation(String name, String address, String phone, String email, UserType userType, Order order) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
        this.order = order;
    }
}
