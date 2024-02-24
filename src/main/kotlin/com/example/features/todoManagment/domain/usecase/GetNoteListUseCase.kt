package com.example.features.todoManagment.domain.usecase

import arrow.core.*
import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.domain.failure.*
import com.example.features.todoManagment.domain.reposity.*
import com.example.features.shared.domain.repository.RepositoryFailure.*

class GetNoteListUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): Either<NoteDomainFailure, List<Note>> {
        return noteRepository.getNoteList().mapLeft {
            when (it) {
                is InaccessibleData -> NoteDomainFailure.NoteListUnavailable
                else -> NoteDomainFailure.UnknownError
            }
        }
    }
}