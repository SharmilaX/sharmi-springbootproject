package com.example.SharmiSpringBoot.ProductService.controller;

import com.example.SharmiSpringBoot.ProductService.constants.ProductConstants;
import com.example.SharmiSpringBoot.ProductService.dto.ProductDto;
import com.example.SharmiSpringBoot.ProductService.dto.ResponseDto;
import com.example.SharmiSpringBoot.ProductService.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("/createProduct")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductDto productDto) {
        iProductService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.PRODUCT_MSG_201));
    }

    @GetMapping("/fetchProduct")
    public ResponseEntity<ProductDto> fetchProduct(@RequestParam String productCode) {
        ProductDto productDto = iProductService.fetchProduct(productCode);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody ProductDto productDto) {
        boolean isUpdated = iProductService.updateProduct(productDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<ResponseDto> deleteProduct(@RequestParam String productCode) {
        boolean isDeleted = iProductService.deleteProduct(productCode);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }
}
