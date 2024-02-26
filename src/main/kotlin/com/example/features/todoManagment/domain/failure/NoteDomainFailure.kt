package com.example.features.todoManagment.domain.failure

import com.example.features.shared.domain.entity.*

sealed class NoteDomainFailure : Failure {
    data object UnknownError  : NoteDomainFailure()
    data object NotFound  : NoteDomainFailure()
    data object NoteListUnavailable: NoteDomainFailure()
    data object NoteDetailUnavailable: NoteDomainFailure()
    data object NoteCreateUnavailable: NoteDomainFailure()
    data object NoteUpdateUnavailable: NoteDomainFailure()
    data object NoteDeleteUnavailable: NoteDomainFailure()
}
