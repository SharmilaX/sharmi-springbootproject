package com.example.SharmiSpringBoot.ProductService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_details")
public class ProductDetails extends BaseEntity {

    public ProductDetails(Long details_id, Long productId, String detail1, String detail2, String detail3) {
        this.details_id = details_id;
        this.productId = productId;
        this.detail1 = detail1;
        this.detail2 = detail2;
        this.detail3 = detail3;
    }

    public ProductDetails() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long details_id;

    @Column(name = "product_id")
    private Long productId;

    @Column
    private String detail1;

    @Column
    private String detail2;

    @Column
    private String detail3;

    public Long getDetails_id() {
        return details_id;
    }

    public void setDetails_id(Long details_id) {
        this.details_id = details_id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDetail1() {
        return detail1;
    }

    public void setDetail1(String detail1) {
        this.detail1 = detail1;
    }

    public String getDetail2() {
        return detail2;
    }

    public void setDetail2(String detail2) {
        this.detail2 = detail2;
    }

    public String getDetail3() {
        return detail3;
    }

    public void setDetail3(String detail3) {
        this.detail3 = detail3;
    }

}
