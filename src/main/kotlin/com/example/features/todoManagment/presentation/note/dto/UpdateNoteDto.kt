package com.example.features.todoManagment.presentation.note.dto

import kotlinx.serialization.*

@Serializable
data class UpdateNoteDto(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
)
