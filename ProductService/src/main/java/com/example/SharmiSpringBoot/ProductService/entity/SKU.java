package com.example.SharmiSpringBoot.ProductService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor
@Table(name = "skus")
public class SKU extends BaseEntity {

    public SKU(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        super(createdAt, createdBy, updatedAt, updatedBy);
    }

    public SKU() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sku_id;

    @Column(name = "product_id")
    private Long productId;

    @Column(unique = true)
    private String skuCode;

    @Column
    private String color;

    @Column
    private String size;

    @Column
    private Integer stockQuantity;

    @Column
    private Double skuPrice;

    @Column
    private String status;

    public Long getSku_id() { return sku_id; }
    public void setSku_id(Long sku_id) { this.sku_id = sku_id; }

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
