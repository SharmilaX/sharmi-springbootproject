package com.example.SharmiSpringBoot.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SkuDto {

    private Long productId;
    private String skuCode;
    private String color;
    private String size;
    private Integer stockQuantity;
    private Double skuPrice;
    private String status;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getSkuCode() { return skuCode; }
    public void setSkuCode(String skuCode) { this.skuCode = skuCode; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Double getSkuPrice() { return skuPrice; }
    public void setSkuPrice(Double skuPrice) { this.skuPrice = skuPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
