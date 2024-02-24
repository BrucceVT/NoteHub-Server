package com.example.features.todoManagment.presentation.note.dto

import com.example.features.todoManagment.presentation.note.serialization.*
import kotlinx.serialization.*
import java.util.*

@Serializable
data class NoteDTO(
    @SerialName("id")
    @Serializable(UUIDSerializer::class)
    val id: UUID,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: Long,
    @SerialName("updated_at")
    val updatedAt: Long
)