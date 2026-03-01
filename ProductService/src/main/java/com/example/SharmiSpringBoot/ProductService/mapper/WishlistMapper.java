package com.example.SharmiSpringBoot.ProductService.mapper;

import com.example.SharmiSpringBoot.ProductService.dto.WishlistDto;
import com.example.SharmiSpringBoot.ProductService.entity.Product;
import com.example.SharmiSpringBoot.ProductService.entity.Wishlist;

public class WishlistMapper {

    public static WishlistDto mapToWishlistDto(Wishlist wishlist, Product product, WishlistDto wishlistDto) {
        wishlistDto.setWishlistId(wishlist.getWishlist_id());
        wishlistDto.setUserEmail(wishlist.getUserEmail());
        wishlistDto.setProductId(wishlist.getProductId());
        wishlistDto.setProductCode(product.getProductCode());
        wishlistDto.setProductName(product.getProductName());
        wishlistDto.setAddedAt(wishlist.getAddedAt());
        return wishlistDto;
    }
}
