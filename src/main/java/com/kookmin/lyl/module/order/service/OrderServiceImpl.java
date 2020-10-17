package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderDeliveryStatus;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.dto.*;
import com.kookmin.lyl.module.order.repository.OrderProductRepository;
import com.kookmin.lyl.module.order.repository.OrderRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;


    @Override
    public void orderProductWish(@NonNull Order order, @NonNull OrderProductCreate orderProductCreate) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProductNumber(orderProductCreate.getProduct().getProductNumber());
        orderProduct.setProductName(orderProductCreate.getProduct().getName());
        orderProduct = orderProductRepository.save(orderProduct);   // 앙 저장띠
    }

    // TODO:: 일단 하나씩은 있다고 가정을 하고 들어갈 거에요
    // Member 가 만들어지는 순간 OrderRepo에 wish랑 cart 가 2개를 생성한다고 보자



    @Override
    public void orderProductCart(@NonNull Order order,@NonNull  OrderProductCreate orderProductCreate,@NonNull  List<ProductOption> productOptions) {
        OrderProduct orderProduct = new OrderProduct();              // 수량 결정하고
        for (ProductOption productOption : productOptions){
            orderProduct.addOrderProductOption(productOption);  // 장바구니에 담을 때에는 선택 옵션들을 넣어야지
        }
        orderProduct.setQuantity(orderProductCreate.getProductQuantity());  // 수량 변경 하시고
        orderProduct.setOrder(order);
        orderProduct.setProductNumber(orderProductCreate.getProduct().getProductNumber());
        orderProduct.setProductPrice(orderProductCreate.getProduct().getPrice());
        orderProduct.setProductName(orderProductCreate.getProduct().getName());

        orderProduct = orderProductRepository.save(orderProduct);   // 앙 저장띠
    }

    @Override
    public void cartToDealed(OrderMemberOrderType orderMemberOrderType, @NonNull OrderCartToDealed orderCartToDealed) {
        List<Order> orders = orderRepository.findByMemberIdAndOrderType(orderMemberOrderType.getMemberId(), orderMemberOrderType.getOrderType().toString());
        for(Order order:orders){
            order.setOrderType(OrderType.DEALED);
        }
    }


    // OrderProduct를 만드는 것이당
    @Override
    public void orderProductCreate(OrderProductCreate orderProductCreate, Product product) {
        OrderProduct orderProduct = OrderProduct.builder()
                .quantity(orderProductCreate.getProductQuantity())
                .product(product)
                .productOptions(orderProductCreate.)
                .build();
        orderProduct = orderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderDetail> findByMemberIdAndOrderType(OrderMemberOrderType orderMemberOrderType) {
        List<Order> orders = orderRepository.findByMemberIdAndOrderType(orderMemberOrderType.getMemberId(), orderMemberOrderType.getOrderType().toString());
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Order order : orders){
            orderDetails.add(new OrderDetail(order));
        }
        return orderDetails;
    }


    // 관심상품 로직//
    private void intoWish(Product product, OrderProduct... orderProducts) {
        List<OrderProduct> findProducts = orderProductRepository.findByProductNumber(product.getProductNumber());
        // ProductNumber 로 검색을 할 시에 없다면 추가를 해준다.
        Order order = new Order();

        if (findProducts.isEmpty()) {
            Order order = new Order();
            order.setMember(member);
            order.setOrderType(OrderType.WISH);
        }
    }




    // 장바구니 로직//
    private void intoCart(Member member, Product product, OrderProduct... orderProducts){
        List<OrderProduct> findProducts = orderProductRepository.findByProductNumber(product.getProductNumber());

        if (findProducts.isEmpty()) {
            Order order = new Order();
            order.setMember(member);
            order.setOrderType(OrderType.CART);
        }else{
            for(OrderProduct orderProduct : orderProducts){
                if((orderProduct.getProductNumber()).equals(product.getProductNumber())){
                    orderProduct.setQuantity(orderProduct.getQuantity()+1); // 장바구니에 있는 항목들 또 담을 시 장바구니에 quantity++;
                }
            }
        }
    }

    public List<OrderProduct> greaterThanPrice(int price){
        return orderProductRepository.findByProductPriceGreaterThanOrderByProductPrice(price);
    }

    public List<OrderProduct> lessThanPrice(int price){
        return orderProductRepository.findByProductPriceLessThanOrderByProductPriceDesc(price);
    }

    public List<OrderProduct> containingName(String productName){
        return orderProductRepository.findByProductNameContaining(productName);
    }

    public List<OrderProduct> findAllOrderByProductPrice(){
        return orderProductRepository.findAllOrderByProductPrice();
    }

    // TODO: 자 이거 뭔가 쎄하지? 이런거 확인을 어떻게 하는지 제발 좀 알아볼까?
    public List<Order> findOrderByOrderedAtOrderByOrderedAtDesc(LocalDateTime startDate, LocalDateTime endDate){
        return orderRepository.findOrderByOrderedAtOrderByOrderedAtDesc(startDate, endDate);
    }

    // TODO: 이렇게 말고 이름을 findGroupByStatus 이지랄 하면 안되나?
    public List<Order> findOrderByStatusGroupByStatus(OrderDeliveryStatus status){
        return orderRepository.findOrderByStatusGroupByStatus(status);
    }
    public List<Order> findByOrderTypeGroupByOrderType(OrderType orderType){
        return orderRepository.findByOrderTypeGroupByOrderType(orderType);
    }


}
