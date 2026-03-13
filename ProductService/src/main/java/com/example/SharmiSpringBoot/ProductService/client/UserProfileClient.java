package com.example.SharmiSpringBoot.ProductService.client;

import com.example.SharmiSpringBoot.ProductService.client.dto.UserDto;
import com.example.SharmiSpringBoot.ProductService.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserProfileClient {

    private final RestClient restClient;

    public UserProfileClient(RestClient.Builder builder,
                             @Value("${userprofile.service.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public UserDto fetchUser(String email) {
        return restClient.get()
                .uri("/api/fetchUser?email={email}", email)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), (request, response) -> {
                    throw new ResourceNotFoundException("User not found with email: " + email);
                })
                .onStatus(status -> status.is5xxServerError(), (request, response) -> {
                    throw new RuntimeException("UserProfile service error while fetching user: " + email);
                })
                .body(UserDto.class);
    }
}
