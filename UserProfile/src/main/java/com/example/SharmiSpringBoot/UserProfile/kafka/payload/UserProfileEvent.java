package com.example.SharmiSpringBoot.UserProfile.kafka.payload;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileEvent {

    private String eventId = UUID.randomUUID().toString();
    private String eventType = "CREATE_PROFILE";
    private LocalDateTime timestamp = LocalDateTime.now();

    private String name;
    private String email;
    private String phoneNumber;

    // optional - null if address not provided
    private AddressDto address;
}
