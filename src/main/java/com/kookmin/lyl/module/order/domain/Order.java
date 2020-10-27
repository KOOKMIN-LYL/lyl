package com.kookmin.lyl.module.order.domain;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
// @RequiredArgsConstructor
@Table(name="ORDER")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;   // 주문 id -> 0 : wish(관심상품 리스트), 1 : cart(장바구니 리스트), 나머지 : 실제 주문(리스트)

    @Column(name = "ORDERED_AT")
    private LocalDateTime orderedAt;    // 주문 시간

    @Column(name = "TOTAL_PRICE")
    private int totalPrice; // 총 금액

    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress; // 배송지 정보

    

    @Column(name = "REQUEST")
    private String request; // 요청사항

    @Column(name = "ISCHECKED")
    private int isCheked;   // 카트에서 바로 몇개 선택해서 주문할라고

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderDeliveryStatus status; // 주문 상태

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_TYPE")
    private OrderType orderType;    // 상품의 타입 - 관심, 장바구니, 거래완료

    // N:1 의 관계. 1명의 Member는 여러 Order을 한다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;  // 주문 회원

    // 1:N 의 관계. 1개의 Order에는 1개 이상의 product들이 있다
    // 여러 개의 product들을 List로 가져온다
    // TODO:: FetchType 좀 확실히 알아봐 그래야 어떻게 가져오는지 암.
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();

    // N:1 의 관계. 1개의 Shop은 여러 Order 을 가지지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SHOP_ID")
    private Shop shop;

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

    // TODO:: 이거 맑고 Constructor 을 만들어야댐
//    //==주문 생성 메서드==//
//    // 근데 이거는 하나만 바로 주문을 할 때에 그런거 아니냐
//    @Builder
//    private static Order createOrder(Member member, OrderType orderType ,String tempAddress, OrderProduct orderProducts) {
//
//        Order order = new Order();
//        order.setMember(member);
//        for (OrderProduct orderProduct : orderProducts) {
//            order.addOrderProduct(orderProduct);
//        }
//        order.setStatus(OrderDeliveryStatus.ORDERED);   // 주문 생성시 "주문 들어감" 으로 설정
//        order.setOrderedAt(LocalDateTime.now());    // 주문 생성시 "현재 시각" 으로 설정
//        order.setOrderType(orderType);    // 위시냐 장바구니냐 뭐냐
//
//        if(tempAddress != null){
//            order.setDeliveryAddress(order.getDeliveryAddress());   // 멤버의 주소지로 배송지 설정
//        }
//        else{
//            order.setDeliveryAddress(tempAddress);  // 임시 주소지를 따로 입력했다면 임시 주소지로 배송지를 설정
//        }
//        return order;
//    }


    //비즈니스 로직//
    /** 주문 취소 */
    public void cancel() {
        // 이미 배송이 완료가 된 거면 취소가 불가하다고 해야지
        if (status == OrderDeliveryStatus.DELIVERED) {
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        // 배송이 완료가 된 상품이 아니라면 취소를 해줘야지
        else{
            for (OrderProduct orderProduct : orderProducts) {
                status = OrderDeliveryStatus.CANCELLED;
            }
        }

    }


}
