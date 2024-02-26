package com.example.features.todoManagment.infraestruture.persistence.entity

import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.presentation.note.dto.*

fun CreateNoteDto.toNote() = Note(
    title = title,
    content = content,
)
