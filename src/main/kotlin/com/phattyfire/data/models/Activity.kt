package com.phattyfire.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import javax.management.monitor.StringMonitor

data class Activity(
    @BsonId
    val id: String = ObjectId().toString(),
    val timestamp: Long,
    val byUserId: String,
    val toUserId: String,
    val type: Int,
    val parentId: String

)