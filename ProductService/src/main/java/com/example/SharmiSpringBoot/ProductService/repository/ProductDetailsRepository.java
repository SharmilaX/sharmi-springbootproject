package com.example.SharmiSpringBoot.ProductService.repository;

import com.example.SharmiSpringBoot.ProductService.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    Optional<ProductDetails> findByProductId(Long productId);

}
