package com.example.SharmiSpringBoot.ProductService.service.impl;

import com.example.SharmiSpringBoot.ProductService.dto.WishlistDto;
import com.example.SharmiSpringBoot.ProductService.entity.Product;
import com.example.SharmiSpringBoot.ProductService.entity.Wishlist;
import com.example.SharmiSpringBoot.ProductService.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.ProductService.exception.WishlistAlreadyExistException;
import com.example.SharmiSpringBoot.ProductService.client.UserProfileClient;
import com.example.SharmiSpringBoot.ProductService.mapper.WishlistMapper;
import com.example.SharmiSpringBoot.ProductService.repository.ProductRepository;
import com.example.SharmiSpringBoot.ProductService.repository.WishlistRepository;
import com.example.SharmiSpringBoot.ProductService.service.IWishlistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements IWishlistService {

    private WishlistRepository wishlistRepository;
    private ProductRepository productRepository;
    private UserProfileClient userProfileClient;

    @Override
    public void addToWishlist(String email, String productCode) {
        // Validate user exists via UserProfile service (throws ResourceNotFoundException on 404)
        userProfileClient.fetchUser(email);

        // Validate product exists
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + productCode));

        // Check if already wishlisted
        Optional<Wishlist> existing = wishlistRepository.findByUserEmailAndProductId(email, product.getProduct_id());
        if (existing.isPresent()) {
            throw new WishlistAlreadyExistException("Product " + productCode + " is already in wishlist for user: " + email);
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUserEmail(email);
        wishlist.setProductId(product.getProduct_id());
        wishlist.setAddedAt(LocalDateTime.now());
        wishlistRepository.save(wishlist);
    }

    @Override
    public List<WishlistDto> fetchWishlistByUser(String email) {
        // Validate user exists via UserProfile service (throws ResourceNotFoundException on 404)
        userProfileClient.fetchUser(email);

        List<Wishlist> wishlistItems = wishlistRepository.findAllByUserEmail(email);
        return wishlistItems.stream().map(wishlist -> {
            Product product = productRepository.findById(wishlist.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for wishlist item"));
            return WishlistMapper.mapToWishlistDto(wishlist, product, new WishlistDto());
        }).collect(Collectors.toList());
    }

    @Override
    public boolean removeFromWishlist(String email, String productCode) {
        boolean isDeleted = false;

        // Validate user exists via UserProfile service (throws ResourceNotFoundException on 404)
        userProfileClient.fetchUser(email);

        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + productCode));

        Optional<Wishlist> wishlist = wishlistRepository.findByUserEmailAndProductId(email, product.getProduct_id());
        if (wishlist.isPresent()) {
            wishlistRepository.deleteById(wishlist.get().getWishlist_id());
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<WishlistDto> fetchUsersByProduct(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + productCode));

        List<Wishlist> wishlistItems = wishlistRepository.findAllByProductId(product.getProduct_id());
        return wishlistItems.stream()
                .map(wishlist -> WishlistMapper.mapToWishlistDto(wishlist, product, new WishlistDto()))
                .collect(Collectors.toList());
    }
}
