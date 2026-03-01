package com.example.SharmiSpringBoot.ProductService.controller;

import com.example.SharmiSpringBoot.ProductService.constants.ProductConstants;
import com.example.SharmiSpringBoot.ProductService.dto.ResponseDto;
import com.example.SharmiSpringBoot.ProductService.dto.WishlistDto;
import com.example.SharmiSpringBoot.ProductService.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = {MediaType.APPLICATION_JSON_VALUE})
public class WishlistController {

    @Autowired
    private IWishlistService iWishlistService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addToWishlist(@RequestParam String email,
                                                     @RequestParam String productCode) {
        iWishlistService.addToWishlist(email, productCode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, "Product added to wishlist successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<WishlistDto>> fetchWishlistByUser(@RequestParam String email) {
        List<WishlistDto> wishlist = iWishlistService.fetchWishlistByUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(wishlist);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ResponseDto> removeFromWishlist(@RequestParam String email,
                                                          @RequestParam String productCode) {
        boolean isDeleted = iWishlistService.removeFromWishlist(email, productCode);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417));
    }

    @GetMapping("/fetchByProduct")
    public ResponseEntity<List<WishlistDto>> fetchUsersByProduct(@RequestParam String productCode) {
        List<WishlistDto> wishlist = iWishlistService.fetchUsersByProduct(productCode);
        return ResponseEntity.status(HttpStatus.OK).body(wishlist);
    }
}
