package com.example.SharmiSpringBoot.UserProfile.service.impl;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import com.example.SharmiSpringBoot.UserProfile.entity.User;
import com.example.SharmiSpringBoot.UserProfile.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.UserProfile.exception.UserAlreadyExistException;
import com.example.SharmiSpringBoot.UserProfile.mapper.AddressMapper;
import com.example.SharmiSpringBoot.UserProfile.mapper.UserMapper;
import com.example.SharmiSpringBoot.UserProfile.repository.AddressRepository;
import com.example.SharmiSpringBoot.UserProfile.repository.UserRepository;
import com.example.SharmiSpringBoot.UserProfile.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.SharmiSpringBoot.UserProfile.repository.UserRepository.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Override
    public void createUser(UserDto userDto) {
        User user = UserMapper.maptoUser(userDto, new User());
        Optional<User> optionalUser;
        optionalUser = userRepository.findByName(userDto.getName());

        if(optionalUser.isPresent()) {
            throw new UserAlreadyExistException("User already registered with given name "
                    +userDto.getName());
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Anonymous");
        user.getUpdatedAt(LocalDateTime.now());
        user.getUpdatedBy(user.getName());
        User saveUser = userRepository.save(user);
    }

      @Override
    public UserDto fetchUser(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("The User is unavailable"));
        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        return userDto;

        }
    @Override
    public boolean deleteUser(String email){
        boolean isDeleted = false;
        if(!email.isEmpty()) {
           Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User is unavailable")));
           if(user.isPresent()) {
               User existingUser = user.get();
               userRepository.deleteById(existingUser.getUser_id());
               isDeleted = true;
           }

        }
        return isDeleted;
    }
//
//    @Override
//    public UserDto fetchUser(String mobileNumber){
//
//    return userDto;
//
//    }
//
    @Override
    public boolean updateUser(UserDto userDto){
        boolean isUpdated=false;
        if(userDto != null){
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()).orElseThrow(
                    () -> new ResourceNotFoundException("User is unavailable")
            ));
            if(optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                //Long userId = existingUser.getUser_id();
                User user = UserMapper.maptoUser(userDto, existingUser);
                userRepository.save(user);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    @Override
    public void createAddress(AddressDto addressDto, String email) {
        if (!email.isEmpty()) {
            Optional<User> optionUser = userRepository.findByEmail(email);
            if (optionUser.isPresent()) {
                User user = optionUser.get();
                addressDto.setUserId(user.getUser_id());
                Address address = AddressMapper.maptoAddress(addressDto, new Address());
                addressRepository.save(address);
            }
        }
    }

    @Override
    public boolean updateAddress(AddressDto addressDto, String email){
        boolean isUpdated=false;
        if(email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            User user = optionalUser.get();
            Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.findByUserId(user.getUser_id()).orElseThrow(
                    () -> new ResourceNotFoundException("User ADDRESS is unavailable")
            ));
            if(optionalAddress.isPresent()) {
                Address existingAddr = optionalAddress.get();
                Long userId = existingAddr.getUserId();
                Long addressId = existingAddr.getAddress_id();
                Address address = AddressMapper.maptoAddress(addressDto, existingAddr);
                address.setAddress_id(addressId);
                address.setUserId(userId);
                addressRepository.save(address);
                isUpdated = true;
            }
        }
//
        return isUpdated;
    }

    @Override
    public Address fetchAddress(String email) {
        Address address = null;
        if(email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                Long userId=user.getUser_id();
                Optional<Address> optionalAddress = addressRepository.findByUserId(userId);
                address = optionalAddress.get();
            }
        }
        return address;
    }

    @Override
    public boolean deleteAddress(String email) {
        boolean isDeleted = false;
        if(email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Long userId = user.getUser_id();
                Optional<Address> optionalAddress = addressRepository.findByUserId(userId);
               Address address = optionalAddress.get();
               addressRepository.deleteById(address.getAddress_id());
               isDeleted = true;
            }
        }
        return isDeleted;
    }
}
