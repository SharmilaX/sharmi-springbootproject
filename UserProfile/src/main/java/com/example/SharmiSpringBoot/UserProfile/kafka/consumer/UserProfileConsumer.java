package com.example.SharmiSpringBoot.UserProfile.kafka.consumer;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.kafka.config.KafkaTopicConfig;
import com.example.SharmiSpringBoot.UserProfile.kafka.payload.UserProfileEvent;
import com.example.SharmiSpringBoot.UserProfile.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class UserProfileConsumer {

    private final IUserService iUserService;

    @KafkaListener(topics = KafkaTopicConfig.USER_PROFILE_TOPIC, groupId = "user-profile-group")
    public void consumeUserProfileEvent(UserProfileEvent event) {
        log.info("Received UserProfileEvent | eventId: {} | email: {}", event.getEventId(), event.getEmail());

        UserDto userDto = new UserDto();
        userDto.setName(event.getName());
        userDto.setEmail(event.getEmail());
        userDto.setPhoneNumber(event.getPhoneNumber());
        iUserService.createUser(userDto);
        log.info("User created from Kafka event | email: {}", event.getEmail());

        AddressDto address = event.getAddress();
        if (address != null) {
            iUserService.createAddress(address, event.getEmail());
            log.info("Address created from Kafka event | email: {}", event.getEmail());
        }
    }
}
