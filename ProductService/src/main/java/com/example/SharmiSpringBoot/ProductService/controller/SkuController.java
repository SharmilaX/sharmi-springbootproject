package com.example.SharmiSpringBoot.ProductService.controller;

import com.example.SharmiSpringBoot.ProductService.constants.ProductConstants;
import com.example.SharmiSpringBoot.ProductService.dto.ResponseDto;
import com.example.SharmiSpringBoot.ProductService.dto.SkuDto;
import com.example.SharmiSpringBoot.ProductService.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/sku", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SkuController {

    @Autowired
    private ISkuService iSkuService;

    @PostMapping("/createSku")
    public ResponseEntity<ResponseDto> createSku(@RequestBody SkuDto skuDto) {
        iSkuService.createSku(skuDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.SKU_MSG_201));
    }

    @GetMapping("/fetchSku")
    public ResponseEntity<SkuDto> fetchSku(@RequestParam String skuCode) {
        SkuDto skuDto = iSkuService.fetchSku(skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(skuDto);
    }

    @PutMapping("/updateSku")
    public ResponseEntity<ResponseDto> updateSku(@RequestBody SkuDto skuDto) {
        boolean isUpdated = iSkuService.updateSku(skuDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }

    @DeleteMapping("/deleteSku")
    public ResponseEntity<ResponseDto> deleteSku(@RequestParam String skuCode) {
        boolean isDeleted = iSkuService.deleteSku(skuCode);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }
}
