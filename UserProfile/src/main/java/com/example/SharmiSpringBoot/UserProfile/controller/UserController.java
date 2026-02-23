package com.example.SharmiSpringBoot.UserProfile.controller;

import com.example.SharmiSpringBoot.UserProfile.constants.UserConstants;
import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.ResponseDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import com.example.SharmiSpringBoot.UserProfile.entity.User;
import com.example.SharmiSpringBoot.UserProfile.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

@Autowired
private IUserService iUserService;

    @GetMapping("/sayHello")
    public String sayHello() {
         return  "Helloo World";
    }

    @PostMapping("/createUser")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) throws Exception {
        iUserService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201,UserConstants.MESSAGE_201));

    }

    @GetMapping("/fetchUser")
    public ResponseEntity<UserDto> fetchUser(@RequestParam String email) {
       UserDto user =  iUserService.fetchUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<ResponseDto> updateUser (@RequestBody UserDto userDto) {
        boolean isUpdated = iUserService.updateUser(userDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<ResponseDto> deleteUser (@RequestParam String email) {
        boolean isDeleted = iUserService.deleteUser(email);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417));
    }

    @PostMapping("/createAddress")
    public ResponseEntity<ResponseDto> CreateAddress(@RequestBody AddressDto addressDto, @RequestParam String email) {
        iUserService.createAddress(addressDto, email);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.ADDRESS_201, UserConstants.ADDRESS_MSG_201));
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<ResponseDto> updateAddress (@RequestBody AddressDto addressDto, String email) {
        boolean isUpdated = iUserService.updateAddress(addressDto, email);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417));
    }


    @GetMapping("/fetchAddress")
    public Address fetchAddress (@RequestParam String email) {
       Address address =  iUserService.fetchAddress(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(address).getBody();
    }

    @DeleteMapping("/deleteAddress")
    public boolean deleteAddress(@RequestParam String email) {
        boolean isDeleted = iUserService.deleteAddress(email);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200)).hasBody();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417)).hasBody();

    }

}
