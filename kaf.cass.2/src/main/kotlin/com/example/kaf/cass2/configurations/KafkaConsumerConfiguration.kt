package com.example.kaf.cass2.configurations

import com.example.kaf.cass2.models.ClickEvent
import com.example.kaf.cass2.models.ImpressionEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.connect.json.JsonDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.util.HashMap

@Configuration
class KafkaConsumerConfiguration {
    @Bean
    fun fconsumerFactory(): DefaultKafkaConsumerFactory<String, ImpressionEvent> {
        val config: HashMap<String, Any> = HashMap()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ConsumerConfig.GROUP_ID_CONFIG] = "group_impressionEvent"
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        return DefaultKafkaConsumerFactory<String, ImpressionEvent>(config,
                StringDeserializer(),
                org.springframework.kafka.support.serializer.JsonDeserializer(ImpressionEvent::class.java))

    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ImpressionEvent> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, ImpressionEvent>()
        factory.consumerFactory = fconsumerFactory()
        return factory
    }

    @Bean
    fun clickEventConsumerFactory(): DefaultKafkaConsumerFactory<String, ClickEvent> {
        val config: HashMap<String, Any> = HashMap()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ConsumerConfig.GROUP_ID_CONFIG] = "group_clickEvent"
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        return DefaultKafkaConsumerFactory<String, ClickEvent>(config,
                StringDeserializer(),
                org.springframework.kafka.support.serializer.JsonDeserializer(ClickEvent::class.java))

    }

    @Bean
    fun clickEventKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ClickEvent> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, ClickEvent>()
        factory.consumerFactory = clickEventConsumerFactory()
        return factory
    }

}