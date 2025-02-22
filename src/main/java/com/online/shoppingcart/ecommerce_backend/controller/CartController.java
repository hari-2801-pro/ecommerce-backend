package com.online.shoppingcart.ecommerce_backend.controller;

import com.online.shoppingcart.ecommerce_backend.model.CartEntity;
import com.online.shoppingcart.ecommerce_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")  // Ensure frontend can access API
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartEntity> getCart() {
        return cartService.getAllCartItems();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartEntity cartItem) {
        try {
            cartService.addToCart(cartItem);  // Add the item to the cart (service logic)
            return ResponseEntity.ok(cartItem);  // Return the updated cart item
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error adding product to cart: " + e.getMessage());
        }
    }
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return ResponseEntity.ok("Product removed successfully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok("Cart cleared successfully");
    }



    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody CartEntity cartItem) {
        cartService.updateQuantity(cartItem.getProductId(), cartItem.getQuantity());
        return ResponseEntity.ok("Quantity updated successfully");
    }

    @PutMapping("/updateDeliveryOption")
    public ResponseEntity<String> updateDeliveryOption(@RequestBody CartEntity cartItem) {
        cartService.updateDeliveryOption(cartItem.getProductId(), cartItem.getDeliveryOptionId());
        return ResponseEntity.ok("Delivery option updated successfully");
    }
}


