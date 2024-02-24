package com.example.features.shared.domain.repository

sealed class RepositoryFailure(val message: String) {
    object UnknownError  : RepositoryFailure("Error desconocido")
    object NotFound  : RepositoryFailure("No se encontro la informacion solicitada")
    object InaccessibleData : RepositoryFailure("Fuente de datos inaccesible")
    data class DatabaseError(val cause: Throwable) : RepositoryFailure("Error en la base de datos")
}