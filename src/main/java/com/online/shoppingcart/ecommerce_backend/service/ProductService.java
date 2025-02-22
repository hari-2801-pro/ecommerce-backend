package com.online.shoppingcart.ecommerce_backend.service;

import com.online.shoppingcart.ecommerce_backend.model.ProductEntity;
import com.online.shoppingcart.ecommerce_backend.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;

    // Get all products
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Add a new product
    public List<ProductEntity> addProducts(List<ProductEntity> products) {
        return productRepository.saveAll(products);
    }


    // Update an existing product
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setImageUrl(updatedProduct.getImageUrl());
            product.setRating(updatedProduct.getRating());
            product.setPrice(updatedProduct.getPrice());
            product.setKeywords(updatedProduct.getKeywords());
            return productRepository.save(product);
        }).orElse(null);
    }

    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE products AUTO_INCREMENT = 1").executeUpdate();
    }

}

