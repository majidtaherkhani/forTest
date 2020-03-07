package com.example.kaf.cass2.services

import com.example.kaf.cass2.models.ClickEvent
import com.example.kaf.cass2.models.ImpressionEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaListener(val cassandraService: CassandraService) {

    @KafkaListener(topics = ["impressionEventTopic"],groupId ="group_impressionEvent")
    fun impressionEventConsumer(impressionEvent: ImpressionEvent) {
        cassandraService.storeInCassandra(impressionEvent)
    }
    @KafkaListener(topics = ["clickEventTopic"],groupId ="group_clickEvent")
    fun clickEventConsumer(clickEvent: ClickEvent) {
        cassandraService.updateCassandra(clickEvent)
    }
}
