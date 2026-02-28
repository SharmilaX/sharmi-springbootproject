package com.example.SharmiSpringBoot.ProductService.mapper;

import com.example.SharmiSpringBoot.ProductService.dto.SkuDto;
import com.example.SharmiSpringBoot.ProductService.entity.SKU;

public class SkuMapper {

    public static SkuDto mapToSkuDto(SKU sku, SkuDto skuDto) {
        skuDto.setProductId(sku.getProductId());
        skuDto.setSkuCode(sku.getSkuCode());
        skuDto.setColor(sku.getColor());
        skuDto.setSize(sku.getSize());
        skuDto.setStockQuantity(sku.getStockQuantity());
        skuDto.setSkuPrice(sku.getSkuPrice());
        skuDto.setStatus(sku.getStatus());
        return skuDto;
    }

    public static SKU mapToSku(SkuDto skuDto, SKU sku) {
        sku.setProductId(skuDto.getProductId());
        sku.setSkuCode(skuDto.getSkuCode());
        sku.setColor(skuDto.getColor());
        sku.setSize(skuDto.getSize());
        sku.setStockQuantity(skuDto.getStockQuantity());
        sku.setSkuPrice(skuDto.getSkuPrice());
        sku.setStatus(skuDto.getStatus());
        return sku;
    }
}
