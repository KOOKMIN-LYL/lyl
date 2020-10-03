package com.kookmin.lyl.module.product.domain;

import lombok.Builder;
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

    @Builder
    public ProductOption(String option, ProductOptionType type, Product product) {
        this.option = option;
        this.type = type;
        changeProduct(product);
    }

    public void changeProduct(Product product) {
        //TODO:: 양방향 참조 관계일 경우 각각의 레퍼런스가 중복되거나 동기화되지 않는 경우를 고려하는 로직이 필요하다.
        this.product = product;
    }
}
