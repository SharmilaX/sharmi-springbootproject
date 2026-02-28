package com.example.SharmiSpringBoot.ProductService.controller;

import com.example.SharmiSpringBoot.ProductService.constants.ProductConstants;
import com.example.SharmiSpringBoot.ProductService.dto.CategoryDto;
import com.example.SharmiSpringBoot.ProductService.dto.ResponseDto;
import com.example.SharmiSpringBoot.ProductService.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/category", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<ResponseDto> createCategory(@RequestBody CategoryDto categoryDto) {
        iCategoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.CATEGORY_MSG_201));
    }

    @GetMapping("/fetchCategory")
    public ResponseEntity<CategoryDto> fetchCategory(@RequestParam String categoryCode) {
        CategoryDto categoryDto = iCategoryService.fetchCategory(categoryCode);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<ResponseDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        boolean isUpdated = iCategoryService.updateCategory(categoryDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<ResponseDto> deleteCategory(@RequestParam String categoryCode) {
        boolean isDeleted = iCategoryService.deleteCategory(categoryCode);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }
}
