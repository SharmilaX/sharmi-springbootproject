package com.example.SharmiSpringBoot.UserProfile.repository;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository  extends JpaRepository<Address, Long> {
    Optional<Address> findByUserId(Long userId);

}
