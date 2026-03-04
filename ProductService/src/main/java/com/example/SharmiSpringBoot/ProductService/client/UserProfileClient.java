package com.example.SharmiSpringBoot.ProductService.client;

import com.example.SharmiSpringBoot.ProductService.feignclient.dto.UserDto;
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
                .body(UserDto.class);
    }
}
