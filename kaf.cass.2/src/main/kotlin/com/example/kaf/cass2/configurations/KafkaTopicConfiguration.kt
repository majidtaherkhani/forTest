package com.example.kaf.cass2.configurations

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfiguration {
    @Bean
    fun impressionEventTopic(): NewTopic? {
        return TopicBuilder.name("impressionEventTopic")
                .partitions(10)
                .replicas(3)
                .compact()
                .build()
    }
    @Bean
    fun clickEventTopic(): NewTopic? {
        return TopicBuilder.name("clickEventTopic")
                .partitions(10)
                .replicas(3)
                .compact()
                .build()
    }
}