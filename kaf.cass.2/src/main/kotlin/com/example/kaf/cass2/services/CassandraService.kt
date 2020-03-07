package com.example.kaf.cass2.services

import com.example.kaf.cass2.models.AdEvent
import com.example.kaf.cass2.models.ClickEvent
import com.example.kaf.cass2.models.ImpressionEvent
import com.example.kaf.cass2.repository.cassandraRepo
import org.springframework.stereotype.Service

@Service
class CassandraService(val cassandraRepo: cassandraRepo) {
    fun storeInCassandra(impressionEvent: ImpressionEvent) {
        cassandraRepo.save(convertToAdEvent(impressionEvent))
    }

    fun convertToAdEvent(impressionEvent: ImpressionEvent): AdEvent {
        return AdEvent(impressionEvent.requestId,
                impressionEvent.adId,
                impressionEvent.adTitle,
                impressionEvent.advertiserCost,
                impressionEvent.appId,
                impressionEvent.appTitle,
                impressionEvent.impressionTime,
                0)

    }

    fun updateCassandra(clickEvent: ClickEvent){
        cassandraRepo.findByRequestId(clickEvent.requestId).clickTime=clickEvent.clickTime
    }
}

