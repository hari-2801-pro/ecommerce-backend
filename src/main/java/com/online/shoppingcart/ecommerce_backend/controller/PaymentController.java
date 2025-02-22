package com.online.shoppingcart.ecommerce_backend.controller;

import com.online.shoppingcart.ecommerce_backend.model.CartRequest;
import com.online.shoppingcart.ecommerce_backend.model.OrderEntity;
import com.online.shoppingcart.ecommerce_backend.model.OrderStatus;
import com.online.shoppingcart.ecommerce_backend.repository.OrderRepository;
import com.online.shoppingcart.ecommerce_backend.service.OrderService;
import com.online.shoppingcart.ecommerce_backend.service.PayPalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypal")
@CrossOrigin("*")
public class PaymentController {

	 private final PayPalService payPalService;
	   private final OrderService orderService; 
	   @Autowired
	    public PaymentController(PayPalService payPalService, OrderService orderService) {
	        this.payPalService = payPalService;
	        this.orderService = orderService;
	    }

    @PostMapping("/pay")
    public String pay() {
        try {
        	Payment payment = payPalService.createPayment(
        	        10.0, "USD", "paypal",  // ✅ Change "INR" to "USD"
        	        "sale", "Payment for Order",
        	        "http://localhost:8080/paypal/cancel",
        	        "http://localhost:8080/paypal/success");


            return payment.getLinks().stream()
                    .filter(link -> "approval_url".equals(link.getRel()))
                    .map(link -> link.getHref())
                    .findFirst()
                    .orElse("Error in payment process");
        } catch (PayPalRESTException e) {
            return "Error: " + e.getMessage();
        }
    }
    @GetMapping("/success")
    public ResponseEntity<Void> success(@RequestParam("paymentId") String paymentId, 
                                        @RequestParam("PayerID") String payerId) {
        try {
            payPalService.executePayment(paymentId, payerId);
            
            // ✅ Redirect to success.html (served from frontend)
            return ResponseEntity.status(HttpStatus.FOUND)
                                 .location(URI.create("http://127.0.0.1:5500/quickcart/success.html"))
                                 .build();
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



  
	@GetMapping("/cancel")
    public String cancel() {
        return "Payment Cancelled.";
    }
}
