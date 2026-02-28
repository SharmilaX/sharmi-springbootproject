package com.example.SharmiSpringBoot.ProductService.service.impl;

import com.example.SharmiSpringBoot.ProductService.dto.CategoryDto;
import com.example.SharmiSpringBoot.ProductService.entity.Category;
import com.example.SharmiSpringBoot.ProductService.exception.CategoryAlreadyExistException;
import com.example.SharmiSpringBoot.ProductService.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.ProductService.mapper.CategoryMapper;
import com.example.SharmiSpringBoot.ProductService.repository.CategoryRepository;
import com.example.SharmiSpringBoot.ProductService.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto, new Category());
        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryDto.getCategoryCode());
        if (optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistException("Category already registered with given code "
                    + categoryDto.getCategoryCode());
        }
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy("Anonymous");
        category.getUpdatedAt(LocalDateTime.now());
        category.getUpdatedBy(category.getCategoryName());
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto fetchCategory(String categoryCode) {
        Category category = categoryRepository.findByCategoryCode(categoryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Category is unavailable"));
        return CategoryMapper.mapToCategoryDto(category, new CategoryDto());
    }

    @Override
    public boolean updateCategory(CategoryDto categoryDto) {
        boolean isUpdated = false;
        if (categoryDto != null) {
            Optional<Category> optionalCategory = Optional.ofNullable(
                    categoryRepository.findByCategoryCode(categoryDto.getCategoryCode())
                            .orElseThrow(() -> new ResourceNotFoundException("Category is unavailable"))
            );
            if (optionalCategory.isPresent()) {
                Category existingCategory = optionalCategory.get();
                Category category = CategoryMapper.mapToCategory(categoryDto, existingCategory);
                categoryRepository.save(category);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCategory(String categoryCode) {
        boolean isDeleted = false;
        if (!categoryCode.isEmpty()) {
            Optional<Category> category = Optional.ofNullable(
                    categoryRepository.findByCategoryCode(categoryCode)
                            .orElseThrow(() -> new ResourceNotFoundException("Category is unavailable"))
            );
            if (category.isPresent()) {
                categoryRepository.deleteById(category.get().getCategory_id());
                isDeleted = true;
            }
        }
        return isDeleted;
    }
}
