package com.example.features.todoManagment.infraestruture.persistence.service

import arrow.core.*
import com.example.features.todoManagment.domain.entity.Note
import com.example.features.todoManagment.domain.failure.NoteDomainFailure
import com.example.features.todoManagment.domain.services.NoteService
import com.example.features.todoManagment.domain.usecase.*
import java.util.*

class NoteServiceExposed(
    val getNoteListUseCase: GetNoteListUseCase,
    val getNoteDetailUseCase: GetNoteDetailUseCase,
    val createNoteUseCase: CreateNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
): NoteService  {
    override suspend fun getNoteList(): Either<NoteDomainFailure, List<Note>> {
        return getNoteListUseCase()
    }

    override suspend fun getNoteDetail(id: UUID): Either<NoteDomainFailure, Note> {
        return getNoteDetailUseCase(id)
    }

    override suspend fun createNote(note: Note): Either<NoteDomainFailure, Note> {
        return createNoteUseCase(note)
    }

    override suspend fun updateNote(note: Note): Either<NoteDomainFailure, Note> {
        return updateNoteUseCase(note)
    }

    override suspend fun deleteNote(id: UUID): Either<NoteDomainFailure, Unit> {
        return deleteNoteUseCase(id)
    }
}