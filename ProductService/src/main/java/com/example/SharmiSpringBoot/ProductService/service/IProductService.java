package com.example.SharmiSpringBoot.ProductService.service;

import com.example.SharmiSpringBoot.ProductService.dto.ProductDto;

import java.util.List;

public interface IProductService {

    void createProduct(ProductDto productDto);
    ProductDto fetchProduct(String productCode);
    boolean updateProduct(ProductDto productDto);
    boolean deleteProduct(String productCode);
    List<ProductDto> fetchActiveProducts();
}