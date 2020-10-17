package com.kookmin.lyl.module.order.domain;


import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name="ORDER_PRODUCT")
public class OrderProduct {
    @Id @GeneratedValue
    @Column(name = "ORDER_PRODUCT_ID")
    private Long orderProductId;

    @Column(name = "IS_CHECKED")
    private boolean isChecked;      // default = 1 : 체크가 된 상태

    @Column
    private List<ProductOption> orderProductOptions  = new ArrayList<ProductOption>();

    @Column(name = "ORDER_PRODUCT_OPTION")
    private String option;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_NUMBER")
    private Long productNumber;


    @Column(name = "PRODUCT_PRICE")
    private int productPrice;

    @Column(name = "QUANTITY")
    private int quantity;

    // 1:N 인 관계 연결 부분
    @ManyToOne(fetch = FetchType.LAZY)
    // ORDER_ID 를 기준으로
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    //==조회 로직==//
    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        // 상품의 가격 * 상품의 수량
        return getProductPrice() * getQuantity();
    }

    // 옵션들 더하기
    public void addOrderProductOption(ProductOption productOption) {
        orderProductOptions.add(productOption);
    }


    // 생성부
    @Builder
    public static OrderProduct createOrderProduct(int quantity, Product product, ProductOption... productOptions){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setQuantity(quantity); // 수량
        orderProduct.setProductPrice(product.getPrice());   // 가격 가져오고
        orderProduct.setProductName(product.getName()); // 상품 이름 가져오고
        orderProduct.setProductNumber(product.getProductNumber());  // 상품 번호 가져오고
        for(ProductOption productOption : productOptions){
            orderProduct.addOrderProductOption(productOption);
        }
        return orderProduct;
    }
}
