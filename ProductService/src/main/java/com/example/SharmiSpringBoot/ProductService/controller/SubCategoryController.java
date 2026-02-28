package com.example.SharmiSpringBoot.ProductService.controller;

import com.example.SharmiSpringBoot.ProductService.constants.ProductConstants;
import com.example.SharmiSpringBoot.ProductService.dto.ResponseDto;
import com.example.SharmiSpringBoot.ProductService.dto.SubCategoryDto;
import com.example.SharmiSpringBoot.ProductService.service.ISubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/subCategory", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SubCategoryController {

    @Autowired
    private ISubCategoryService iSubCategoryService;

    @PostMapping("/createSubCategory")
    public ResponseEntity<ResponseDto> createSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        iSubCategoryService.createSubCategory(subCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.SUBCATEGORY_MSG_201));
    }

    @GetMapping("/fetchSubCategory")
    public ResponseEntity<SubCategoryDto> fetchSubCategory(@RequestParam String subCategoryCode) {
        SubCategoryDto subCategoryDto = iSubCategoryService.fetchSubCategory(subCategoryCode);
        return ResponseEntity.status(HttpStatus.OK).body(subCategoryDto);
    }

    @PutMapping("/updateSubCategory")
    public ResponseEntity<ResponseDto> updateSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        boolean isUpdated = iSubCategoryService.updateSubCategory(subCategoryDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }

    @DeleteMapping("/deleteSubCategory")
    public ResponseEntity<ResponseDto> deleteSubCategory(@RequestParam String subCategoryCode) {
        boolean isDeleted = iSubCategoryService.deleteSubCategory(subCategoryCode);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }
}
