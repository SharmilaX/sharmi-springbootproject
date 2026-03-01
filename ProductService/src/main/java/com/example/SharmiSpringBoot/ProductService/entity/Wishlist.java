package com.example.SharmiSpringBoot.ProductService.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    public Wishlist() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlist_id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public Long getWishlist_id() { return wishlist_id; }
    public void setWishlist_id(Long wishlist_id) { this.wishlist_id = wishlist_id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
