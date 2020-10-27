package com.kookmin.lyl.web;

import com.kookmin.lyl.module.order.dto.OrderDetails;
import com.kookmin.lyl.module.order.dto.OrderProductInfo;
import com.kookmin.lyl.module.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/cart/product")
    public String addProductToCart(OrderProductInfo orderProductInfo, Principal principal) {
        orderService.addCart(principal.getName(), orderProductInfo);

        return "ok";
    }

    @GetMapping(value = "/cart")
    public OrderDetails getCart(Principal principal) {
        return orderService.findCartOrderDetails(principal.getName());
    }

    @DeleteMapping(value = "/cart/product")
    public String deleteProductFromCart(Long orderProductId) {
        orderService.cancelOrderProduct(orderProductId);

        return "ok";
    }

    @GetMapping(value = "/order/{orderId}")
    public OrderDetails getOrderDetail(@PathVariable("orderId") Long orderId) {
        return orderService.findOrderDetails(orderId);
    }
}
