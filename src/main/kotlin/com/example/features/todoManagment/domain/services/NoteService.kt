package com.example.features.todoManagment.domain.services

import arrow.core.Either
import com.example.features.todoManagment.domain.entity.Note
import com.example.features.todoManagment.domain.failure.*
import java.util.UUID

interface NoteService {
    suspend fun getNoteList(): Either<NoteDomainFailure,List<Note>>

    suspend fun getNoteDetail(id: UUID): Either<NoteDomainFailure,Note>

    suspend fun createNote(note: Note): Either<NoteDomainFailure,Note>

    suspend fun updateNote(note: Note): Either<NoteDomainFailure,Note>

    suspend fun deleteNote(id: UUID): Either<NoteDomainFailure,Unit>
}
