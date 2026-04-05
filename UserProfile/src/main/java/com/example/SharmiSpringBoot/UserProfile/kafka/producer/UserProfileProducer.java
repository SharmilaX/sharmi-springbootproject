package com.example.SharmiSpringBoot.UserProfile.kafka.producer;

import com.example.SharmiSpringBoot.UserProfile.kafka.config.KafkaTopicConfig;
import com.example.SharmiSpringBoot.UserProfile.kafka.payload.UserProfileEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@AllArgsConstructor
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class UserProfileProducer {

    private final KafkaTemplate<String, UserProfileEvent> kafkaTemplate;

    public void publishUserProfileEvent(UserProfileEvent event) {
        CompletableFuture<SendResult<String, UserProfileEvent>> future =
                kafkaTemplate.send(KafkaTopicConfig.USER_PROFILE_TOPIC, event.getEmail(), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to publish UserProfileEvent for email: {} | error: {}",
                        event.getEmail(), ex.getMessage());
            } else {
                log.info("UserProfileEvent published | email: {} | partition: {} | offset: {}",
                        event.getEmail(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        });
    }
}
