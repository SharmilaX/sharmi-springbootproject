package com.example.SharmiSpringBoot.ProductService.mapper;

import com.example.SharmiSpringBoot.ProductService.dto.ProductDto;
import com.example.SharmiSpringBoot.ProductService.entity.Product;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product, ProductDto productDto) {
        productDto.setSubCategoryId(product.getSubCategoryId());
        productDto.setProductName(product.getProductName());
        productDto.setProductCode(product.getProductCode());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        productDto.setPrice(product.getPrice());
        productDto.setStatus(product.getStatus());
        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto, Product product) {
        product.setSubCategoryId(productDto.getSubCategoryId());
        product.setProductName(productDto.getProductName());
        product.setProductCode(productDto.getProductCode());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setStatus(productDto.getStatus());
        return product;
    }
}
