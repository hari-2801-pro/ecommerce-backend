package com.online.shoppingcart.ecommerce_backend.service;

import com.online.shoppingcart.ecommerce_backend.model.CartEntity;
import com.online.shoppingcart.ecommerce_backend.repository.CartRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<CartEntity> getAllCartItems() {
        return cartRepository.findAll();
    }

    public void addToCart(CartEntity cartItem) {
        Optional<CartEntity> existingItem = cartRepository.findByProductId(cartItem.getProductId());

        if (existingItem.isPresent()) {
            CartEntity item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            cartRepository.save(item);
        } else {
            cartRepository.save(cartItem);
        }
    }

    @Transactional
    public void removeFromCart(Long productId) {
        if (!cartRepository.findByProductId(productId).isPresent()) {
            throw new RuntimeException("Product not found in cart.");
        }
        cartRepository.deleteByProductId(productId);
    }
    
    public void clearCart() {
        cartRepository.deleteAll(); // Removes all items from the cart
    }

    public void updateQuantity(Long productId, Integer quantity) {
        Optional<CartEntity> cartItem = cartRepository.findByProductId(productId);
        cartItem.ifPresent(item -> {
            item.setQuantity(quantity);
            cartRepository.save(item);
        });
    }

    public void updateDeliveryOption(Long productId, Long deliveryOptionId) {
        Optional<CartEntity> cartItem = cartRepository.findByProductId(productId);
        cartItem.ifPresent(item -> {
            item.setDeliveryOptionId(deliveryOptionId);
            cartRepository.save(item);
        });
    }
}

