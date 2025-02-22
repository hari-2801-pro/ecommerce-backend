package com.online.shoppingcart.ecommerce_backend.model;

public class CartItem {
    private Long productId;
    private Integer quantity;
    private Integer deliveryOptionId;

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

    public Integer getDeliveryOptionId() {
        return deliveryOptionId;
    }

    public void setDeliveryOptionId(Integer deliveryOptionId) {
        this.deliveryOptionId = deliveryOptionId;
    }
}
