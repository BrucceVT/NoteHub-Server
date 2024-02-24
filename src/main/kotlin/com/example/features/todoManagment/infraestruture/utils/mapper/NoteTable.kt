package com.example.features.todoManagment.infraestruture.utils.mapper

import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.infraestruture.persistence.entity.*

fun NoteTableEntity.toNote(): Note {
    return Note(
        id = id.value,
        title = title,
        content = content,
        createdAt = createdat,
        updatedAt = updatedat
    )
}