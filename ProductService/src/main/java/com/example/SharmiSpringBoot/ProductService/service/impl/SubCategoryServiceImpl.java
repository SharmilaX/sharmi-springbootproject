package com.example.SharmiSpringBoot.ProductService.service.impl;

import com.example.SharmiSpringBoot.ProductService.dto.SubCategoryDto;
import com.example.SharmiSpringBoot.ProductService.entity.SubCategory;
import com.example.SharmiSpringBoot.ProductService.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.ProductService.exception.SubCategoryAlreadyExistException;
import com.example.SharmiSpringBoot.ProductService.mapper.SubCategoryMapper;
import com.example.SharmiSpringBoot.ProductService.repository.SubCategoryRepository;
import com.example.SharmiSpringBoot.ProductService.service.ISubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubCategoryServiceImpl implements ISubCategoryService {

    private SubCategoryRepository subCategoryRepository;

    @Override
    public void createSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = SubCategoryMapper.mapToSubCategory(subCategoryDto, new SubCategory());
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findBySubCategoryCode(subCategoryDto.getSubCategoryCode());
        if (optionalSubCategory.isPresent()) {
            throw new SubCategoryAlreadyExistException("SubCategory already registered with given code "
                    + subCategoryDto.getSubCategoryCode());
        }
        subCategory.setCreatedAt(LocalDateTime.now());
        subCategory.setCreatedBy("Anonymous");
        subCategory.getUpdatedAt(LocalDateTime.now());
        subCategory.getUpdatedBy(subCategory.getSubCategoryName());
        subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategoryDto fetchSubCategory(String subCategoryCode) {
        SubCategory subCategory = subCategoryRepository.findBySubCategoryCode(subCategoryCode)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategory is unavailable"));
        return SubCategoryMapper.mapToSubCategoryDto(subCategory, new SubCategoryDto());
    }

    @Override
    public boolean updateSubCategory(SubCategoryDto subCategoryDto) {
        boolean isUpdated = false;
        if (subCategoryDto != null) {
            Optional<SubCategory> optionalSubCategory = Optional.ofNullable(
                    subCategoryRepository.findBySubCategoryCode(subCategoryDto.getSubCategoryCode())
                            .orElseThrow(() -> new ResourceNotFoundException("SubCategory is unavailable"))
            );
            if (optionalSubCategory.isPresent()) {
                SubCategory existingSubCategory = optionalSubCategory.get();
                SubCategory subCategory = SubCategoryMapper.mapToSubCategory(subCategoryDto, existingSubCategory);
                subCategoryRepository.save(subCategory);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    @Override
    public boolean deleteSubCategory(String subCategoryCode) {
        boolean isDeleted = false;
        if (!subCategoryCode.isEmpty()) {
            Optional<SubCategory> subCategory = Optional.ofNullable(
                    subCategoryRepository.findBySubCategoryCode(subCategoryCode)
                            .orElseThrow(() -> new ResourceNotFoundException("SubCategory is unavailable"))
            );
            if (subCategory.isPresent()) {
                subCategoryRepository.deleteById(subCategory.get().getSubCategory_id());
                isDeleted = true;
            }
        }
        return isDeleted;
    }
}
