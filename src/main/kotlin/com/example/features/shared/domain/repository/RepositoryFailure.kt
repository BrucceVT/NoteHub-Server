package com.example.features.shared.domain.repository

import com.example.features.shared.domain.entity.*

sealed class RepositoryFailure() : Failure {
    object UnknownError  : RepositoryFailure()
    object NotFound  : RepositoryFailure()
    object InaccessibleData : RepositoryFailure()
    data class DatabaseError(val cause: Throwable) : RepositoryFailure()
}