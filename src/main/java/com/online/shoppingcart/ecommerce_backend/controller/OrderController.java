package com.online.shoppingcart.ecommerce_backend.controller;

import com.online.shoppingcart.ecommerce_backend.model.OrderEntity;
import com.online.shoppingcart.ecommerce_backend.model.CartRequest;
import com.online.shoppingcart.ecommerce_backend.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody CartRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request, true));
    }
}
