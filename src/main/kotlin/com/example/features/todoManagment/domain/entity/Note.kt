package com.example.features.todoManagment.domain.entity

import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var content: String,
    val createdAt: Long,
    var updatedAt: Long
)