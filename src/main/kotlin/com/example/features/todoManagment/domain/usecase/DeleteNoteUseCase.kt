package com.example.features.todoManagment.domain.usecase

import arrow.core.*
import com.example.features.todoManagment.domain.failure.*
import com.example.features.todoManagment.domain.reposity.*
import com.example.features.shared.domain.repository.RepositoryFailure.*
import java.util.*

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: UUID): Either<NoteDomainFailure, Unit> {
        return noteRepository.deleteNote(id).mapLeft {
            when (it) {
                is InaccessibleData -> NoteDomainFailure.NoteDeleteUnavailable
                is NotFound -> NoteDomainFailure.NotFound
                else -> NoteDomainFailure.UnknownError
            }
        }
    }
}