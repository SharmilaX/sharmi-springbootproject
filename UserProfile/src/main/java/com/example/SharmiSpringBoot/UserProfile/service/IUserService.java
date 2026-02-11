package com.example.SharmiSpringBoot.UserProfile.service;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;

public interface IUserService {

    void createUser( UserDto userDto);
    boolean deleteUser(String email);
    UserDto fetchUser(String email);
    boolean updateUser(UserDto userDto);
    void createAddress(AddressDto addressDto, String email);
    boolean updateAddress(AddressDto addressDto, String email);
    Address fetchAddress(String email);
    boolean deleteAddress(String email);

}
