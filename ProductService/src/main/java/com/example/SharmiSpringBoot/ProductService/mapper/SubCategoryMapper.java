package com.example.SharmiSpringBoot.ProductService.mapper;

import com.example.SharmiSpringBoot.ProductService.dto.SubCategoryDto;
import com.example.SharmiSpringBoot.ProductService.entity.SubCategory;

public class SubCategoryMapper {

    public static SubCategoryDto mapToSubCategoryDto(SubCategory subCategory, SubCategoryDto subCategoryDto) {
        subCategoryDto.setCategoryId(subCategory.getCategoryId());
        subCategoryDto.setSubCategoryCode(subCategory.getSubCategoryCode());
        subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
        subCategoryDto.setDescription(subCategory.getDescription());
        return subCategoryDto;
    }

    public static SubCategory mapToSubCategory(SubCategoryDto subCategoryDto, SubCategory subCategory) {
        subCategory.setCategoryId(subCategoryDto.getCategoryId());
        subCategory.setSubCategoryCode(subCategoryDto.getSubCategoryCode());
        subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
        subCategory.setDescription(subCategoryDto.getDescription());
        return subCategory;
    }
}
