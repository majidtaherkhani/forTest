package com.example.kaf.cass2.repository

import com.example.kaf.cass2.models.AdEvent
import org.springframework.data.cassandra.repository.CassandraRepository

interface cassandraRepo:CassandraRepository<AdEvent,String> {
    fun findByRequestId(id: String):AdEvent
}