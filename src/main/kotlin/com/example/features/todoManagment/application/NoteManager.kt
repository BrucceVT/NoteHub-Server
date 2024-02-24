package com.example.features.todoManagment.application

import arrow.core.Either
import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.domain.failure.*
import com.example.features.todoManagment.domain.usecase.*
import java.util.*

class NoteManager (
    val getNoteListUseCase: GetNoteListUseCase,
    val getNoteDetailUseCase: GetNoteDetailUseCase,
    val createNoteUseCase: CreateNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
) {

    suspend fun getNoteList(): Either<NoteDomainFailure, List<Note>> {
        return getNoteListUseCase()
    }

    suspend fun getNoteDetail(id: UUID): Either<NoteDomainFailure, Note> {
        return getNoteDetailUseCase(id)
    }

    suspend fun createNote(note: Note): Either<NoteDomainFailure, Note> {
        return createNoteUseCase(note)
    }

    suspend fun updateNote(note: Note): Either<NoteDomainFailure, Note> {
        return updateNoteUseCase(note)
    }

    suspend fun deleteNote(id: UUID): Either<NoteDomainFailure, Unit> {
        return deleteNoteUseCase(id)
    }
}