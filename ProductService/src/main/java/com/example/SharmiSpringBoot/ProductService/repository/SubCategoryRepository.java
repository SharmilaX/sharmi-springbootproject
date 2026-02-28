package com.example.SharmiSpringBoot.ProductService.repository;

import com.example.SharmiSpringBoot.ProductService.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    Optional<SubCategory> findBySubCategoryCode(String subCategoryCode);
    Optional<SubCategory> findBySubCategoryName(String subCategoryName);
}
