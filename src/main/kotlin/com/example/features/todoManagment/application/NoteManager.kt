package com.example.features.todoManagment.application

import arrow.core.Either
import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.domain.failure.*
import com.example.features.todoManagment.domain.services.NoteService
import java.util.*

class NoteManager (
    private val noteService: NoteService
) {

    suspend fun getNoteList(): Either<NoteDomainFailure, List<Note>> {
        return noteService.getNoteList()
    }

    suspend fun getNoteDetail(id: UUID): Either<NoteDomainFailure, Note> {
        return noteService.getNoteDetail(id)
    }

    suspend fun createNote(note: Note): Either<NoteDomainFailure, Note> {
        return noteService.createNote(note)
    }

    suspend fun updateNote(note: Note): Either<NoteDomainFailure, Note> {
        return noteService.updateNote(note)
    }

    suspend fun deleteNote(id: UUID): Either<NoteDomainFailure, Unit> {
        return noteService.deleteNote(id)
    }
}