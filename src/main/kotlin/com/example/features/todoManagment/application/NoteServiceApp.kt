package com.example.features.todoManagment.application

import arrow.core.Either
import com.example.features.todoManagment.domain.entity.Note
import com.example.features.todoManagment.domain.failure.*
import com.example.features.todoManagment.infraestruture.persistence.entity.toNote
import com.example.features.todoManagment.infraestruture.utils.mapper.toNoteDto
import com.example.features.todoManagment.infraestruture.utils.mapper.toNoteListDto
import com.example.features.todoManagment.presentation.note.dto.*
import java.util.*

class NoteServiceApp(
    private val noteManager: NoteManager
) {

    suspend fun getNoteList(): Either<NoteDomainFailure, List<NoteDTO>> {
        return noteManager.getNoteList().map { it.toNoteListDto() }
    }

    suspend fun getNoteDetail(id: UUID): Either<NoteDomainFailure, NoteDTO> {
        return noteManager.getNoteDetail(id).map { it.toNoteDto() }
    }

    suspend fun createNote(note: CreateNoteDto): Either<NoteDomainFailure, NoteDTO> {
        return noteManager.createNote(note.toNote()).map { it.toNoteDto() }
    }

    suspend fun updateNote(note: Note): Either<NoteDomainFailure, NoteDTO> {
        return noteManager.updateNote(note).map { it.toNoteDto() }
    }

    suspend fun deleteNote(id: UUID): Either<NoteDomainFailure, Unit> {
        return noteManager.deleteNote(id)
    }
}