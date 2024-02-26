package com.example.features.todoManagment.presentation.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.util.*

suspend fun validateAndGetNoteId(call: ApplicationCall): UUID? {
    val noteId = call.parameters["id"]
    if (noteId.isNullOrBlank() || noteId.length > 36) {
        call.respond(HttpStatusCode.BadRequest, "Note ID is missing, empty or too long")
        return null
    }
    try {
        return UUID.fromString(noteId)
    } catch (e: IllegalArgumentException) {
        call.respond(HttpStatusCode.BadRequest, "Note ID is not a valid UUID")
        return null
    }
}