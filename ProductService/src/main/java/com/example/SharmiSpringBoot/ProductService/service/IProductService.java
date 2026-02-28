package com.example.SharmiSpringBoot.ProductService.service;

import com.example.SharmiSpringBoot.ProductService.dto.ProductDto;

public interface IProductService {

    void createProduct(ProductDto productDto);
    ProductDto fetchProduct(String productCode);
    boolean updateProduct(ProductDto productDto);
    boolean deleteProduct(String productCode);
}
