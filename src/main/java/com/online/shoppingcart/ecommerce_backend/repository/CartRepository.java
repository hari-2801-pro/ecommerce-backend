package com.online.shoppingcart.ecommerce_backend.repository;

import com.online.shoppingcart.ecommerce_backend.model.CartEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByProductId(Long productId);

    @Transactional
    void deleteByProductId(Long productId);
}



