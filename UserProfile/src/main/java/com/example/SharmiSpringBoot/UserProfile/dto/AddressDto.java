package com.example.SharmiSpringBoot.UserProfile.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long userId;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;

}
