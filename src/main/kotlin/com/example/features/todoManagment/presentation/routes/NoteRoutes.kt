package com.example.features.todoManagment.presentation.routes

import com.example.features.todoManagment.application.*
import com.example.features.todoManagment.domain.entity.Note
//import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.presentation.note.dto.*

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*
import com.example.features.todoManagment.presentation.utils.*


private const val ENDPOINT = "api/notes"

fun Route.notesRoutes() {
    val noteService: NoteServiceApp by inject()

    route("/$ENDPOINT") {
        get("/secret") { call.respondText(Secret) }
        get {
            val noteListEither = noteService.getNoteList()
            noteListEither.fold(
                { failure ->
                    call.respond(HttpStatusCode.BadRequest, "An error occurred $failure")
                },
                { noteList ->
                    call.respond(HttpStatusCode.OK, noteList)
                }
            )
        }
        get("/{id}") {
            //val noteId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Note ID is missing")
            val noteId = call.parameters["id"]
            if (noteId.isNullOrBlank() || noteId.length > 36) {
                return@get call.respond(HttpStatusCode.BadRequest, "Note ID is missing, empty or too long")
            }
            try {
                val id = UUID.fromString(noteId)
                val noteEither = noteService.getNoteDetail(id)
                noteEither.fold(
                    { failure ->
                        call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                    },
                    { noteList ->
                        call.respond(HttpStatusCode.OK, noteList)
                    }
                )
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, "Note ID is not a valid UUID")
            }
        }
        post {
            try {
                val createdNoteEither = noteService.createNote(call.receive())
                createdNoteEither.fold(
                    { failure ->
                        call.respond(HttpStatusCode.BadRequest, "An error occurred $failure")
                    },
                    { noteList ->
                        call.respond(HttpStatusCode.Created, noteList)
                    }
                )
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "The request body contains invalid fields")
            }
        }
        put("/{id}") {
            val noteId = call.parameters["id"]
            if (noteId.isNullOrBlank() || noteId.length > 36) {
                return@put call.respond(HttpStatusCode.BadRequest, "Note ID is missing, empty or too long")
            }
            try {
                val idUUID: UUID = UUID.fromString(noteId)
                val updatedNote = call.receive<UpdateNoteDto>()
                val resultEither = noteService.updateNote(
                    Note(
                        id = idUUID,
                        title = updatedNote.title,
                        content = updatedNote.content
                    )
                )
                resultEither.fold(
                    { failure ->
                        call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                    },
                    { noteList ->
                        call.respond(HttpStatusCode.OK, noteList)
                    }
                )
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, "Note ID is not a valid UUID")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "The request body contains invalid fields")
            }
        }
        delete("/{id}") {
            val noteId = call.parameters["id"]
            if (noteId.isNullOrBlank() || noteId.length > 36) {
                return@delete call.respond(HttpStatusCode.BadRequest, "Note ID is missing, empty or too long")
            }
            try {
                val id = UUID.fromString(noteId)
                val deleteNoteEither = noteService.deleteNote(id)
                deleteNoteEither.fold(
                    { failure ->
                        call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                    },
                    { noteList ->
                        call.respond(HttpStatusCode.OK, "Successfully removed")
                    }
                )
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, "Note ID is not a valid UUID")
            }

        }
    }

}