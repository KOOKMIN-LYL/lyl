package com.kookmin.lyl.web;

import com.kookmin.lyl.module.order.dto.OrderDetails;
import com.kookmin.lyl.module.order.dto.OrderProductInfo;
import com.kookmin.lyl.module.order.dto.OrderSearchCondition;
import com.kookmin.lyl.module.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/cart/product")
    public String addProductToCart(@RequestBody OrderProductInfo orderProductInfo, Principal principal) {
        orderService.addCart(principal.getName(), orderProductInfo);

        return "ok";
    }

    @GetMapping(value = "/cart")
    public OrderDetails getCart(Principal principal) {
        return orderService.findCartOrderDetails(principal.getName());
    }

    @DeleteMapping(value = "/cart/product/{orderProductId}")
    public String deleteProductFromCart(@PathVariable("orderProductId") Long orderProductId) {
        orderService.cancelOrderProduct(orderProductId);

        return "ok";
    }

    @GetMapping(value = "/order/{orderId}")
    public OrderDetails getOrderDetail(@PathVariable("orderId") Long orderId) {
        return orderService.findOrderDetails(orderId);
    }

    @GetMapping(value = "/order")
    public Page<OrderDetails> getOrderDetailsList(Pageable pageable, OrderSearchCondition condition) {
        return orderService.searchOrderList(pageable, condition);
    }

    @PostMapping(value = "/order/product")
    public String orderProduct(@RequestBody OrderProductInfo orderProductInfo, Principal principal) {
        orderService.orderProduct(principal.getName(), orderProductInfo);
        return "ok";
    }
}
