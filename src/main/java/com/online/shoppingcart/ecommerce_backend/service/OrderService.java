package com.online.shoppingcart.ecommerce_backend.service;

import com.online.shoppingcart.ecommerce_backend.model.*;
import com.online.shoppingcart.ecommerce_backend.repository.OrderRepository;
import com.online.shoppingcart.ecommerce_backend.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAllByOrderByOrderTimeDesc();
    }

    public OrderEntity getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderEntity createOrder(CartRequest request, boolean isPaymentSuccessful) {
        String orderId = UUID.randomUUID().toString();
        LocalDateTime orderTime = LocalDateTime.now();

        int productPriceCents = 0;
        int shippingPriceCents = 0;
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (CartItem item : request.getCart()) {
            OrderProduct product = new OrderProduct();
            product.setProductId(String.valueOf(item.getProductId()));
            product.setQuantity(item.getQuantity());
            product.setEstimatedDeliveryTime(orderTime.plusDays(getDeliveryDays(item.getDeliveryOptionId())));

            // Retrieve product price from the database
            ProductEntity productEntity = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Accumulate product and shipping prices
            productPriceCents += productEntity.getPrice() * item.getQuantity();
            shippingPriceCents += getDeliveryPrice(item.getDeliveryOptionId());

            orderProducts.add(product);
        }

        // Calculate total price including tax
        int totalBeforeTaxCents = productPriceCents + shippingPriceCents;
        int taxCents = (int) (totalBeforeTaxCents * 0.1);
        int totalCostCents = totalBeforeTaxCents + taxCents;

        // Create order
        OrderEntity order = new OrderEntity();
        order.setId(orderId);
        order.setOrderTime(orderTime);
        order.setProducts(orderProducts);
        order.setTotalCostCents(totalCostCents);
        order.setStatus(isPaymentSuccessful ? OrderStatus.SUCCESS : OrderStatus.FAILED);

        return orderRepository.save(order);
    }

    /**
     * Function to get delivery price from static values
     */
    private int getDeliveryPrice(int deliveryOptionId) {
        switch (deliveryOptionId) {
            case 1: return 0;     // 7-day delivery
            case 2: return 499;   // 3-day delivery
            case 3: return 999;   // 1-day delivery
            default: return 0;    // Default to free shipping
        }
    }

    /**
     * Function to get delivery days from static values
     */
    private int getDeliveryDays(int deliveryOptionId) {
        switch (deliveryOptionId) {
            case 1: return 7;
            case 2: return 3;
            case 3: return 1;
            default: return 7; // Default to 7 days
        }
    }
}
