package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.repository.OrderProductRepository;
import com.kookmin.lyl.module.order.repository.OrderRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.repository.ProductOptionRepository;
import com.kookmin.lyl.module.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    public void addCart(@NonNull String memberId, @NonNull Long productId, @NonNull Long productOptionId) {
        Order order = orderRepository.findByMemberIdAndOrderType(memberId, OrderType.CART.toString());
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(EntityNotFoundException::new);

        if(order == null) {
            Member member = memberRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new);

            order = Order.builder()
                    .orderType(OrderType.CART)
                    .member(member)
                    .build();

            orderRepository.save(order);
        }

        OrderProduct orderProduct =
                orderProductRepository.findByOrderIdAndProductIdAndProductOptionId(order.getId(), productId, productOptionId);

        if(orderProduct == null) {
            orderProduct = OrderProduct.builder()
                    .productId(productId)
                    .productName(product.getName())
                    .productOptionId(productOptionId)
                    .productOptions(productOption.getOption())
                    .productPrice(product.getPrice())
                    .quantity(1)
                    .build();
        } else {
            orderProduct.increaseQuantity();
        }
    }
}
