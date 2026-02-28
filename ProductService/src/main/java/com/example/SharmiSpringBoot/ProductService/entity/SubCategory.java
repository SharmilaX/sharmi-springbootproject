package com.example.SharmiSpringBoot.ProductService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor
@Table(name = "sub_categories")
public class SubCategory extends BaseEntity {

    public SubCategory(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(createdAt, createdBy, updatedAt, updatedBy);
    }

    public SubCategory() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategory_id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(unique = true)
    private String subCategoryCode;

    @Column
    private String subCategoryName;

    @Column
    private String description;

    public Long getSubCategory_id() { return subCategory_id; }
    public void setSubCategory_id(Long subCategory_id) { this.subCategory_id = subCategory_id; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getSubCategoryCode() { return subCategoryCode; }
    public void setSubCategoryCode(String subCategoryCode) { this.subCategoryCode = subCategoryCode; }

    public String getSubCategoryName() { return subCategoryName; }
    public void setSubCategoryName(String subCategoryName) { this.subCategoryName = subCategoryName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
