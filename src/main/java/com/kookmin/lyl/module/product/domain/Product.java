package com.kookmin.lyl.module.product.domain;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="PRODUCT")
public class Product {
    @Id @GeneratedValue
    @Column(name="PRODUCT_ID")
    private Long productNumber;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private Integer price;

    @Column(name="MANUFACTURER")
    private String manufacturer;

    @Column(name="ORIGIN")
    private String origin;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SHOP_ID")
    private Shop shop;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption> productOptions = new ArrayList<ProductOption>();
}
