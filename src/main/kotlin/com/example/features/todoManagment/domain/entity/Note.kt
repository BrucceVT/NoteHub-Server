package com.example.features.todoManagment.domain.entity

import com.example.features.todoManagment.infraestruture.utils.time.DateTimeUtils
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var content: String,
    val createdAt: Long = DateTimeUtils.getCurrentDateAsLong(),
    val updatedAt: Long = DateTimeUtils.getCurrentDateAsLong(),
)