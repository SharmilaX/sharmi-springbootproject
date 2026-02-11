package com.example.SharmiSpringBoot.UserProfile.mapper;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import com.example.SharmiSpringBoot.UserProfile.entity.User;

public class AddressMapper {

    public static AddressDto mapToAddressDto(Address address, AddressDto addressDto){
        addressDto.setUserId(address.getUserId());
        addressDto.setAddress1(address.getAddress1());
        addressDto.setAddress2(address.getAddress2());
        addressDto.setAddress3(address.getAddress3());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    public static Address maptoAddress(AddressDto addressDto, Address address) {
        address.setUserId(addressDto.getUserId());
        address.setAddress1(addressDto.getAddress1());
        address.setAddress2(addressDto.getAddress2());
        address.setAddress3(addressDto.getAddress3());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        return address;
    }
}
