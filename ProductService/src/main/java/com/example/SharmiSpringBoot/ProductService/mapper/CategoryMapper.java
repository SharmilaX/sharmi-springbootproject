package com.example.SharmiSpringBoot.ProductService.mapper;

import com.example.SharmiSpringBoot.ProductService.dto.CategoryDto;
import com.example.SharmiSpringBoot.ProductService.entity.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto(Category category, CategoryDto categoryDto) {
        categoryDto.setCategoryCode(category.getCategoryCode());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public static Category mapToCategory(CategoryDto categoryDto, Category category) {
        category.setCategoryCode(categoryDto.getCategoryCode());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
