package com.online.shoppingcart.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId; // Foreign key reference to Product

    private Integer quantity;

    private Long deliveryOptionId; // Foreign key reference to DeliveryOption

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getDeliveryOptionId() {
		return deliveryOptionId;
	}

	public void setDeliveryOptionId(Long deliveryOptionId) {
		this.deliveryOptionId = deliveryOptionId;
	}
}
