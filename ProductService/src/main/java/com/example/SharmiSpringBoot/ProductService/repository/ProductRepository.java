package com.example.SharmiSpringBoot.ProductService.repository;

import com.example.SharmiSpringBoot.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCode(String productCode);
    Optional<Product> findByProductName(String productName);
}
