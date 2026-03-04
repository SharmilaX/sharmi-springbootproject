package com.example.SharmiSpringBoot.ProductService.feignclient;

import com.example.SharmiSpringBoot.ProductService.feignclient.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class UserProfileFeignClient {

    private final RestClient restClient;

    public UserProfileFeignClient(@Value("${userprofile.service.url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public ResponseEntity<UserDto> fetchUser(String email) {
        try {
            UserDto user = restClient.get()
                    .uri("/api/fetchUser?email={email}", email)
                    .retrieve()
                    .body(UserDto.class);
            return ResponseEntity.ok(user);
        } catch (RestClientResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}