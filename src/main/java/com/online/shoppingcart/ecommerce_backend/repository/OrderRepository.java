package com.online.shoppingcart.ecommerce_backend.repository;

import com.online.shoppingcart.ecommerce_backend.model.OrderEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
	 List<OrderEntity> findAllByOrderByOrderTimeDesc();
}
