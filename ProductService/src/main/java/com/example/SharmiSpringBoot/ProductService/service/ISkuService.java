package com.example.SharmiSpringBoot.ProductService.service;

import com.example.SharmiSpringBoot.ProductService.dto.SkuDto;

public interface ISkuService {

    void createSku(SkuDto skuDto);
    SkuDto fetchSku(String skuCode);
    boolean updateSku(SkuDto skuDto);
    boolean deleteSku(String skuCode);
}
