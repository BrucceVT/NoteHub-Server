package com.example.features.todoManagment.infraestruture.utils.mapper

import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.presentation.note.dto.NoteDTO

    fun Note.toNoteDto() = NoteDTO(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    fun List<Note>.toNoteListDto() = map { it.toNoteDto() }
