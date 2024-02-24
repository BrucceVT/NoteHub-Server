package com.example.features.todoManagment.domain.usecase

import arrow.core.*
import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.domain.failure.*
import com.example.features.todoManagment.domain.reposity.*
import com.example.features.shared.domain.repository.RepositoryFailure.*

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository
)  {
    suspend operator fun invoke(note: Note): Either<NoteDomainFailure, Note> {
        return noteRepository.updateNote(note).mapLeft {
            when (it) {
                is InaccessibleData -> NoteDomainFailure.NoteUpdateUnavailable
                else -> NoteDomainFailure.UnknownError
            }
        }
    }
}