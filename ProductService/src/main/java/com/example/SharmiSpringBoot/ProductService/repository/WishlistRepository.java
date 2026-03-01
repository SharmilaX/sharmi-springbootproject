package com.example.SharmiSpringBoot.ProductService.repository;

import com.example.SharmiSpringBoot.ProductService.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.userEmail = :userEmail")
    List<Wishlist> findAllByUserEmail(String userEmail);

    @Query("SELECT w FROM Wishlist w WHERE w.productId = :productId")
    List<Wishlist> findAllByProductId(Long productId);

    Optional<Wishlist> findByUserEmailAndProductId(String userEmail, Long productId);
}
