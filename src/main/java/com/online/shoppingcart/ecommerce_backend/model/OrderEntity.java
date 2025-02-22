package com.online.shoppingcart.ecommerce_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String id; // Store UUID as string
    
    private LocalDateTime orderTime;
    private Integer totalCostCents;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // New field to store SUCCESS or FAILED

    @ElementCollection
    private List<OrderProduct> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getTotalCostCents() {
        return totalCostCents;
    }

    public void setTotalCostCents(Integer totalCostCents) {
        this.totalCostCents = totalCostCents;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
