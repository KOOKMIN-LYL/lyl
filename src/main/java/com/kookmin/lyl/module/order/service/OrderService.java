package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderStatus;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.dto.OrderDetails;
import com.kookmin.lyl.module.order.dto.OrderProductDetails;
import com.kookmin.lyl.module.order.dto.OrderProductInfo;
import com.kookmin.lyl.module.order.dto.OrderSearchCondition;
import com.kookmin.lyl.module.order.repository.OrderProductRepository;
import com.kookmin.lyl.module.order.repository.OrderRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.repository.ProductOptionRepository;
import com.kookmin.lyl.module.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    public Long addCart(@NonNull String memberId, @NonNull OrderProductInfo orderProductInfo) {
        Order order = orderRepository.findCartByMemberMemberIdAndOrderType(memberId, OrderType.CART.toString());
        Product product = productRepository.findById(orderProductInfo.getProductId()).orElseThrow(EntityNotFoundException::new);
        ProductOption productOption = productOptionRepository.findById(orderProductInfo.getProductOptionId())
                .orElseThrow(EntityNotFoundException::new);

        if(order == null) {
            Member member = memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);

            order = Order.builder()
                    .orderType(OrderType.CART)
                    .member(member)
                    .build();

            order = orderRepository.save(order);
        }

        OrderProduct orderProduct =
                orderProductRepository.findByOrderIdAndProductIdAndProductOptionId(order.getId(), product.getId(), productOption.getId());

        if(orderProduct == null) {
            orderProduct = OrderProduct.builder()
                    .order(order)
                    .productId(product.getId())
                    .productName(product.getName())
                    .productOptionId(productOption.getId())
                    .productOptions(productOption.getOption())
                    .productPrice(product.getPrice())
                    .quantity(orderProductInfo.getQuantity())
                    .build();
        } else {
            orderProduct.increaseQuantity(orderProductInfo.getQuantity());
        }

        orderProduct = orderProductRepository.save(orderProduct);

        return order.getId();
    }

    public void orderCart(@NonNull Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.editOrderType(OrderType.ORDER);
        order.editOrderStatus(OrderStatus.PENDING);
    }

    public void orderProduct(@NonNull String memberId, @NonNull OrderProductInfo orderProductInfo) {
        Product product = productRepository.findById(orderProductInfo.getProductId()).orElseThrow(EntityNotFoundException::new);
        ProductOption productOption = productOptionRepository.findById(orderProductInfo.getProductOptionId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);

        Order order = Order.builder()
                .member(member)
                .orderType(OrderType.ORDER)
                .build();

        order.editDeliveryAddress(orderProductInfo.getDeliveryAddress());
        order.editRequest(orderProductInfo.getRequest());

        order = orderRepository.save(order);

        OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .productId(product.getId())
                .productName(product.getName())
                .productOptionId(productOption.getId())
                .productOptions(productOption.getOption())
                .productPrice(product.getPrice())
                .quantity(orderProductInfo.getQuantity())
                .build();

        orderProduct = orderProductRepository.save(orderProduct);
    }

    public void cancelOrder(@NonNull Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.editOrderType(OrderType.CANCELED);
    }

    public Page<OrderDetails> searchOrderList(@NonNull Pageable pageable, @NonNull OrderSearchCondition searchCondition) {
        return orderRepository.searchOrderDetails(pageable, searchCondition);
    }

    public OrderDetails findOrderDetails(@NonNull Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(order.getId());

        List<OrderProductDetails> orderProductDetails = new ArrayList<>();

        for(OrderProduct orderProduct : orderProducts) {
            orderProductDetails.add(new OrderProductDetails(orderProduct));
        }

        OrderDetails orderDetails = new OrderDetails(order);
        orderDetails.setOrderProducts(orderProductDetails);

        return  orderDetails;
    }

    public OrderDetails findCartOrderDetails(@NonNull String memberId) {
        Order order = orderRepository.findCartByMemberMemberIdAndOrderType(memberId, OrderType.CART.toString());
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);

        if(order == null) {
            order = Order.builder()
                    .orderType(OrderType.CART)
                    .member(member)
                    .build();

            order = orderRepository.save(order);
        }

        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(order.getId());
        List<OrderProductDetails> orderProductDetails = new ArrayList<>();

        for(OrderProduct orderProduct : orderProducts) {
            orderProductDetails.add(new OrderProductDetails(orderProduct));
        }

        OrderDetails orderDetails = new OrderDetails(order);
        orderDetails.setOrderProducts(orderProductDetails);

        return  orderDetails;
    }

    public void cancelOrderProduct(@NonNull Long orderProductId) {
        orderProductRepository.deleteById(orderProductId);
    }
}
