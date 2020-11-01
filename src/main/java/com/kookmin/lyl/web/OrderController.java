package com.kookmin.lyl.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kookmin.lyl.module.order.dto.OrderDeliveryInfo;
import com.kookmin.lyl.module.order.dto.OrderDetails;
import com.kookmin.lyl.module.order.dto.OrderProductInfo;
import com.kookmin.lyl.module.order.dto.OrderSearchCondition;
import com.kookmin.lyl.module.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
    public ResponseEntity<Long> orderProduct(@RequestBody OrderProductInfo orderProductInfo, Principal principal) {
        Long orderId = orderService.orderProduct(principal.getName(), orderProductInfo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderId);
    }

    @PostMapping(value = "/cart/order")
    public ResponseEntity<Long> orderProductsInCart(@RequestBody Map<String, List<OrderProductInfo>> params, Principal principal) {
        Long orderId = orderService.orderProduct(principal.getName(), params.get("orderInfos"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderId);
    }

    @PutMapping(value = "/cart/order/{orderId}")
    public ResponseEntity<String> editOrderProductQuantity(@RequestBody OrderProductInfo orderProductInfo,
                                                           @PathVariable("orderId") Long orderId) {
        orderService.editProductQuantity(orderId, orderProductInfo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("수량이 변경되었습니다");
    }

    @PostMapping(value = "/order/purchase/{orderId}")
    public ResponseEntity<String> purchaseOrder(@PathVariable("orderId") Long orderId,
                                                @RequestBody Map<String, Object> params)
            throws JSONException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String request = (String)params.get("request");

        List<OrderDeliveryInfo> orderDeliveryInfos = new ArrayList<>();
        List<Map<String,String>> jsonList = (List<Map<String,String>>)params.get("orderDeliveryInfos");

        for(Map<String, String> map: jsonList) {
            orderDeliveryInfos.add(objectMapper.convertValue(map, OrderDeliveryInfo.class));
        }

        orderService.purchaseOrder(orderId, request, orderDeliveryInfos);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("상품이 주문되었습니다");
    }
}
