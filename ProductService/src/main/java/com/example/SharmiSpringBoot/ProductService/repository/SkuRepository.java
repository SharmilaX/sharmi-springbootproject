package com.example.SharmiSpringBoot.ProductService.repository;

import com.example.SharmiSpringBoot.ProductService.entity.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkuRepository extends JpaRepository<SKU, Long> {

    Optional<SKU> findBySkuCode(String skuCode);
    Optional<SKU> findByProductId(Long productId);
}
