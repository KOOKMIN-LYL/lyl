package com.kookmin.lyl.web;

import com.kookmin.lyl.module.order.dto.OrderProductInfo;
import com.kookmin.lyl.module.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
