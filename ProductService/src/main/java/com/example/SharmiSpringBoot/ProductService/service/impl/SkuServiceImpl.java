package com.example.SharmiSpringBoot.ProductService.service.impl;

import com.example.SharmiSpringBoot.ProductService.dto.SkuDto;
import com.example.SharmiSpringBoot.ProductService.entity.SKU;
import com.example.SharmiSpringBoot.ProductService.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.ProductService.exception.SkuAlreadyExistException;
import com.example.SharmiSpringBoot.ProductService.mapper.SkuMapper;
import com.example.SharmiSpringBoot.ProductService.repository.SkuRepository;
import com.example.SharmiSpringBoot.ProductService.service.ISkuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SkuServiceImpl implements ISkuService {

    private SkuRepository skuRepository;

    @Override
    public void createSku(SkuDto skuDto) {
        SKU sku = SkuMapper.mapToSku(skuDto, new SKU());
        Optional<SKU> optionalSku = skuRepository.findBySkuCode(skuDto.getSkuCode());
        if (optionalSku.isPresent()) {
            throw new SkuAlreadyExistException("SKU already registered with given code "
                    + skuDto.getSkuCode());
        }
        sku.setCreatedAt(LocalDateTime.now());
        sku.setCreatedBy("Anonymous");
        sku.getUpdatedAt(LocalDateTime.now());
        sku.getUpdatedBy(sku.getSkuCode());
        skuRepository.save(sku);
    }

    @Override
    public SkuDto fetchSku(String skuCode) {
        SKU sku = skuRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ResourceNotFoundException("SKU is unavailable"));
        return SkuMapper.mapToSkuDto(sku, new SkuDto());
    }

    @Override
    public boolean updateSku(SkuDto skuDto) {
        boolean isUpdated = false;
        if (skuDto != null) {
            Optional<SKU> optionalSku = Optional.ofNullable(
                    skuRepository.findBySkuCode(skuDto.getSkuCode())
                            .orElseThrow(() -> new ResourceNotFoundException("SKU is unavailable"))
            );
            if (optionalSku.isPresent()) {
                SKU existingSku = optionalSku.get();
                SKU sku = SkuMapper.mapToSku(skuDto, existingSku);
                skuRepository.save(sku);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    @Override
    public boolean deleteSku(String skuCode) {
        boolean isDeleted = false;
        if (!skuCode.isEmpty()) {
            Optional<SKU> sku = Optional.ofNullable(
                    skuRepository.findBySkuCode(skuCode)
                            .orElseThrow(() -> new ResourceNotFoundException("SKU is unavailable"))
            );
            if (sku.isPresent()) {
                skuRepository.deleteById(sku.get().getSku_id());
                isDeleted = true;
            }
        }
        return isDeleted;
    }
}
