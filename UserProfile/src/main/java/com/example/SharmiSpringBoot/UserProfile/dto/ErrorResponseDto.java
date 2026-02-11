package com.example.SharmiSpringBoot.UserProfile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private String errorCode;
    private String errorResponse;
    private LocalDateTime errorTime;

    public ErrorResponseDto(String description, HttpStatus httpStatus, String message, LocalDateTime now) {
    }
}
