package com.example.kaf.cass2.configurations

import com.example.kaf.cass2.models.ClickEvent
import com.example.kaf.cass2.models.ImpressionEvent
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@EnableKafka
class KafkaProducerConfiguration {
    @Bean
    fun producerFactory(): ProducerFactory<String, ImpressionEvent> {
        val config: HashMap<String, Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun impressionEventKafkaTemplate(): KafkaTemplate<String, ImpressionEvent> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun clickEventProducerFactory(): ProducerFactory<String, ClickEvent> {
        val config: HashMap<String, Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun clickEventKafkaTemplate(): KafkaTemplate<String, ClickEvent> {
        return KafkaTemplate(clickEventProducerFactory())
    }
}