package com.example.SharmiSpringBoot.ProductService.feignclient;

import com.example.SharmiSpringBoot.ProductService.feignclient.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserProfile", url = "${userprofile.service.url}")
public interface UserProfileFeignClient {

    @GetMapping("/api/fetchUser")
    ResponseEntity<UserDto> fetchUser(@RequestParam String email);
}
