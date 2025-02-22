package com.online.shoppingcart.ecommerce_backend.model;

import java.util.List;

public class CartRequest {
    private List<CartItem> cart;

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }
}
