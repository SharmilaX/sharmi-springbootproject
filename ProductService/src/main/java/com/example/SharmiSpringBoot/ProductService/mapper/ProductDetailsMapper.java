package com.example.SharmiSpringBoot.ProductService.mapper;

import com.example.SharmiSpringBoot.ProductService.dto.ProductDetailsDto;
import com.example.SharmiSpringBoot.ProductService.entity.ProductDetails;

public class ProductDetailsMapper {

    public static ProductDetailsDto mapToProductDetailsDto(ProductDetails productDetails, ProductDetailsDto productDetailsDto) {
        productDetailsDto.setProductId(productDetails.getProductId());
        productDetailsDto.setDetail1(productDetails.getDetail1());
        productDetailsDto.setDetail2(productDetails.getDetail2());
        productDetailsDto.setDetail3(productDetails.getDetail3());
        return productDetailsDto;
    }

    public static ProductDetails mapToProductDetails(ProductDetailsDto productDetailsDto, ProductDetails productDetails) {
        productDetails.setProductId(productDetailsDto.getProductId());
        productDetails.setDetail1(productDetailsDto.getDetail1());
        productDetails.setDetail2(productDetailsDto.getDetail2());
        productDetails.setDetail3(productDetailsDto.getDetail3());
        return productDetails;
    }
}
