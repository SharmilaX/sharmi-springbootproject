package com.example.SharmiSpringBoot.UserProfile.controller;

import com.example.SharmiSpringBoot.UserProfile.constants.UserConstants;
import com.example.SharmiSpringBoot.UserProfile.dto.ResponseDto;
import com.example.SharmiSpringBoot.UserProfile.kafka.payload.UserProfileEvent;
import com.example.SharmiSpringBoot.UserProfile.kafka.producer.UserProfileProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/profile", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class ProfileController {

    private final UserProfileProducer userProfileProducer;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProfile(@RequestBody UserProfileEvent event) {
        userProfileProducer.publishUserProfileEvent(event);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(UserConstants.STATUS_202, UserConstants.MESSAGE_202));
    }
}
