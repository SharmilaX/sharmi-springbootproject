package com.example.SharmiSpringBoot.ProductService.service;

import com.example.SharmiSpringBoot.ProductService.dto.WishlistDto;

import java.util.List;

public interface IWishlistService {

    void addToWishlist(String email, String productCode);
    List<WishlistDto> fetchWishlistByUser(String email);
    boolean removeFromWishlist(String email, String productCode);
    List<WishlistDto> fetchUsersByProduct(String productCode);
}
