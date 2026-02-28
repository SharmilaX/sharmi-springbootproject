package com.example.SharmiSpringBoot.ProductService.service;

import com.example.SharmiSpringBoot.ProductService.dto.SubCategoryDto;

public interface ISubCategoryService {

    void createSubCategory(SubCategoryDto subCategoryDto);
    SubCategoryDto fetchSubCategory(String subCategoryCode);
    boolean updateSubCategory(SubCategoryDto subCategoryDto);
    boolean deleteSubCategory(String subCategoryCode);
}
