package com.kookmin.lyl.module.order.domain;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="ORDER")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDERED_AT")
    private LocalDateTime orderedAt;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    @Column(name = "REQUEST")
    private String request;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_TYPE")
    private OrderType orderType;

    // N:1 의 관계. 1명의 Member는 여러 Order을 한다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 1:N 의 관계. 1개의 Order에는 1개 이상의 product들이 있다
    // 여러 개의 product들을 List로 가져온다
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    //조회 로직//
    /** 전체 주문 가격 조회 */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getProductPrice();
        }
        return totalPrice;
    }

    //비즈니스 로직//
    /** 주문 취소 */
    public void cancel() {
        // 이미 배송이 완료가 된 거면 취소가 불가하다고 해야지
        if (status == OrderStatus.DELIVERED) {
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        // 배송이 완료가 된 상품이 아니라면 취소를 해줘야지
        else{
            for (OrderProduct orderProduct : orderProducts) {
                status = OrderStatus.CANCELLED;
            }
        }

    }


}
