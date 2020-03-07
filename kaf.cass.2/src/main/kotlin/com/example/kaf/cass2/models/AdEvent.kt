package com.example.kaf.cass2.models

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table
class AdEvent (@PrimaryKey val requestId:String,
               val adId:String,
               val adTitle:String,
               val advertiserCost:Double,
               val appId:String,
               val appTitle: String,
               val impressionTime: Long,
               var clickTime: Long){}