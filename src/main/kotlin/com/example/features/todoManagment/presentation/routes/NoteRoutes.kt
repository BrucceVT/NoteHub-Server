package com.example.features.todoManagment.presentation.routes

import com.example.features.todoManagment.application.*
import com.example.features.todoManagment.domain.entity.Note
import com.example.features.todoManagment.presentation.note.dto.*

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import com.example.features.todoManagment.presentation.utils.*


private const val ENDPOINT = "api/notes"

fun Route.notesRoutes() {
    val noteService: NoteServiceApp by inject()

    route("/$ENDPOINT") {
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
            val noteId = validateAndGetNoteId(call) ?: return@get
            val noteEither = noteService.getNoteDetail(noteId)
            noteEither.fold(
                { failure ->
                    call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                },
                { noteList ->
                    call.respond(HttpStatusCode.OK, noteList)
                }
            )

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
            val noteId = validateAndGetNoteId(call) ?: return@put
            try {
                val updatedNote = call.receive<UpdateNoteDto>()
                val resultEither = noteService.updateNote(
                    Note(
                        id = noteId,
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
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "The request body contains invalid fields")
            }
        }
        delete("/{id}") {
            val noteId = validateAndGetNoteId(call) ?: return@delete
            val deleteNoteEither = noteService.deleteNote(noteId)
            deleteNoteEither.fold(
                { failure ->
                    call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                },
                { noteList ->
                    call.respond(HttpStatusCode.OK, "Successfully removed")
                }
            )


        }
        get("/test-route") { call.respondText(Secret) }
    }

}