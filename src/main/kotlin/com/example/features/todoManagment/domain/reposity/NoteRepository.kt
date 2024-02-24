package com.example.features.todoManagment.domain.reposity

import arrow.core.*
import com.example.features.shared.domain.repository.RepositoryFailure
import com.example.features.todoManagment.domain.entity.Note
import java.util.UUID

interface NoteRepository {
    suspend fun getNoteList(): Either<RepositoryFailure, List<Note>>

    suspend fun getNoteById(id: UUID): Either<RepositoryFailure, Note>

    suspend fun createNote(note: Note): Either<RepositoryFailure, Note>

    suspend fun updateNote(note: Note): Either<RepositoryFailure, Note>

    suspend fun deleteNote(id: UUID): Either<RepositoryFailure, Unit>
}