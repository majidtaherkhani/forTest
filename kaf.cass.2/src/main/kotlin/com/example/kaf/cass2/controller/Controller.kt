package com.example.kaf.cass2.controller

import com.example.kaf.cass2.models.ClickEvent
import com.example.kaf.cass2.models.ImpressionEvent
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(val impressionEventKafkaTemplate: KafkaTemplate<String, ImpressionEvent>,
                 val clickEventKafkaTemplate: KafkaTemplate<String, ClickEvent>,
                 val impressionEventTopic:NewTopic,
                 val clickEventTopic:NewTopic){


    @PostMapping("impression")
    fun receiveImpressionEvent(@RequestBody impressionEvent: ImpressionEvent){
        impressionEventKafkaTemplate.send(impressionEventTopic.name(), impressionEvent)
    }

    @PostMapping("click")
    fun receiveClickEvent(@RequestBody clickEvent: ClickEvent){
        clickEventKafkaTemplate.send(clickEventTopic.name(),clickEvent)
    }

}