package com.example.SharmiSpringBoot.ProductService.repository;

import com.example.SharmiSpringBoot.ProductService.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryCode(String categoryCode);
    Optional<Category> findByCategoryName(String categoryName);
}
