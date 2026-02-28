package com.example.SharmiSpringBoot.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SubCategoryDto {

    private Long categoryId;
    private String subCategoryCode;
    private String subCategoryName;
    private String description;

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getSubCategoryCode() { return subCategoryCode; }
    public void setSubCategoryCode(String subCategoryCode) { this.subCategoryCode = subCategoryCode; }

    public String getSubCategoryName() { return subCategoryName; }
    public void setSubCategoryName(String subCategoryName) { this.subCategoryName = subCategoryName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
