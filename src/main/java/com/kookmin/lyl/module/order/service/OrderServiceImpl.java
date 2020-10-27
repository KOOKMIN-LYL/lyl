package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.member.repository.MemberRepository;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.dto.*;
import com.kookmin.lyl.module.order.repository.OrderProductRepository;
import com.kookmin.lyl.module.order.repository.OrderRepository;
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
    public void orderProductWish(@NonNull OrderProductWish orderProductWish) {

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName(orderProductWish.getProduct().getName());
        orderProduct.setProductNumber(orderProductWish.getProduct().getProductNumber());

        if(orderRepository.findByMemberIdAndOrderType(orderProductWish.getMemberDetails().getMemberId(),OrderType.WISH) != null){
            Order order = orderRepository.findByMemberIdAndOrderType(orderProductWish.getMemberDetails().getMemberId(),OrderType.WISH);
            order.addOrderProduct(orderProduct);
        }else{
            Order order = new Order();
            order.setOrderType(OrderType.WISH);
            order.setMember(memberRepository.findMemberByMemberId(orderProductWish.getMemberDetails().getMemberId()));
            order.setOrderedAt(LocalDateTime.now());
            order.addOrderProduct(orderProduct);
            order = orderRepository.save(order);    // Order을 새로 만든 것이니 저장을 해 주어야 한다.
        }
        orderProduct = orderProductRepository.save(orderProduct);
    }

    @Override
    public void orderProductCart(@NonNull OrderProductCart orderProductCart) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName(orderProductCart.getProduct().getName());
        orderProduct.setProductNumber(orderProductCart.getProduct().getProductNumber());
        orderProduct.setOption(orderProductCart.getOption());
        orderProduct.setProductPrice(orderProductCart.getProduct().getPrice());
        orderProduct.setQuantity(orderProductCart.getQuantity());

        if(orderRepository.findByMemberIdAndOrderType(orderProductCart.getMemberDetails().getMemberId(),OrderType.CART) != null){
            Order order = orderRepository.findByMemberIdAndOrderType(orderProductCart.getMemberDetails().getMemberId(),OrderType.CART);
            order.addOrderProduct(orderProduct);    // TODO:: 이 곳은 한 번 꼭 봐야 하는 그런 곳입니다.
            orderProduct.setOrder(order);   // 잠시만 여기 정말 이상하지 여기는 체크를 해야 할 거 같아.
        }else{
            Order order = new Order();
            order.setOrderType(OrderType.CART);
            order.setMember(memberRepository.findMemberByMemberId(orderProductCart.getMemberDetails().getMemberId()));
            order.setOrderedAt(LocalDateTime.now());
            order.addOrderProduct(orderProduct);
            orderProduct.setOrder(order);
            order = orderRepository.save(order);    // 새로 만들었으니 저장을 해 줘야 겠지요.
        }
        orderProduct = orderProductRepository.save(orderProduct);
    }

    // 카트에서 주문으로 넘어가는 행위
    @Override
    public void orderCartToDeal(@NonNull OrderCartToDeal orderCartToDeal) {

        Order order = orderRepository.findByOrderId(orderCartToDeal.getOrderId());
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderCartToDeal.getOrderId());

        order.setOrderProducts(orderProducts);      // 저 orderProducts를 받는다
        order.setOrderType(OrderType.DEALED);       // CART 에서 DEALED 로 변경한다
        order.setOrderedAt(LocalDateTime.now());    // 장바구니에서 구매로 가니까 구매 시간 바뀌겠지
        order.setDeliveryAddress(orderCartToDeal.getReceiverAddress());   // 구매자랑 받는 사람 주소가 다를 수 있지.
        order.setRequest(orderCartToDeal.getRequest());   // 주문 요청사항 받고
    }

    // OrderProduct를 만드는 것이당
    @Override
    public void orderProductCreate(@NonNull OrderProductCreate orderProductCreate) {
        OrderProduct orderProduct = OrderProduct.builder()
                .quantity(orderProductCreate.getProductQuantity())
                .product(orderProductCreate.getProduct())
                .build();
        List<ProductOption> productOption = orderProductCreate.getProductOptionList();
        for(ProductOption productOptions : productOption){
            orderProduct.addOrderProductOption(productOptions);
        }   // TODO:: 이게 맞는지 확인을 해봐야 한다

        orderProduct = orderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderDetail> findByMemberIdAndOrderType(@NonNull OrderMemberOrderType orderMemberOrderType) {
        List<Order> orders = orderRepository.findByMemberIdAndOrderType(orderMemberOrderType.getMemberId(), orderMemberOrderType.getOrderType().toString());
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Order order : orders){
            orderDetails.add(new OrderDetail(order));
        }
        return orderDetails;
    }

}
