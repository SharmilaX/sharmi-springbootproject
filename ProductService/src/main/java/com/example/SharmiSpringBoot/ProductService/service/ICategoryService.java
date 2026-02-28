package com.example.SharmiSpringBoot.ProductService.service;

import com.example.SharmiSpringBoot.ProductService.dto.CategoryDto;

public interface ICategoryService {

    void createCategory(CategoryDto categoryDto);
    CategoryDto fetchCategory(String categoryCode);
    boolean updateCategory(CategoryDto categoryDto);
    boolean deleteCategory(String categoryCode);
}
