package com.kookmin.lyl.module.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="PRODUCT_OPTION")
public class ProductOption {
    @Id @GeneratedValue
    @Column(name="PRODUCT_OPTION_ID")
    private Long id;

    @Column(name="OPTION")
    private String option;

    @Enumerated(EnumType.STRING)
    @Column(name="PRODUCT_OPTION_TYPE")
    private ProductOptionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;


}
