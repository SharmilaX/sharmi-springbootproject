package com.example.SharmiSpringBoot.UserProfile.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaTopicConfig {

    public static final String USER_PROFILE_TOPIC = "user-profile-events";

    @Bean
    public NewTopic userProfileTopic() {
        return TopicBuilder.name(USER_PROFILE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
