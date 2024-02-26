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

private const val ENDPOINT = "api/notes"

fun Route.notesRoutes() {
    val noteService: NoteServiceApp by inject()

    route("/$ENDPOINT") {
        get("/secret") { call.respondText(Secret) }
        get {
            val noteListEither = noteService.getNoteList()
            noteListEither.fold(
                { failure ->
                    call.respond(HttpStatusCode.BadRequest, "Ocurrio un error $failure")
                },
                { noteList ->
                    call.respond(HttpStatusCode.OK, noteList)
                }
            )
        }
        get("/{id}") {
            val noteId = call.parameters["id"]
            val id = UUID.fromString(noteId)
            if (noteId != null) {
                val noteEither = noteService.getNoteDetail(id)
                noteEither.fold(
                    { failure ->
                        call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                    },
                    { noteList ->
                        call.respond(HttpStatusCode.OK, noteList)
                    }
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "An error occurred")
            }
        }
        post {
            val createdNoteEither = noteService.createNote(call.receive())
            createdNoteEither.fold(
                { failure ->
                    call.respond(HttpStatusCode.BadRequest, "An error occurred $failure")
                },
                { noteList ->
                    call.respond(HttpStatusCode.Created, noteList)
                }
            )
        }
        put("/{id}") {
            val noteId = call.parameters["id"]
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
        }
        delete("/{id}") {
            val noteId = call.parameters["id"]
            val id = UUID.fromString(noteId)
            if (noteId != null) {
                val deleteNoteEither = noteService.deleteNote(id)
                deleteNoteEither.fold(
                    { failure ->
                        call.respond(HttpStatusCode.BadRequest, "Invalid note ID $failure")
                    },
                    { noteList ->
                        call.respond(HttpStatusCode.OK, "Successfully removed")
                    }
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "An error occurred")
            }
        }
    }

}