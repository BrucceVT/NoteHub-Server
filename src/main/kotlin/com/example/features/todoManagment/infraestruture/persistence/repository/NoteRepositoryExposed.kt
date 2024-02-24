package com.example.features.todoManagment.infraestruture.persistence.repository

import arrow.core.*
import arrow.core.raise.*
import com.example.features.shared.domain.repository.*
import com.example.features.todoManagment.domain.entity.*
import com.example.features.todoManagment.domain.reposity.*
import com.example.features.todoManagment.infraestruture.persistence.entity.*
import com.example.features.todoManagment.infraestruture.utils.mapper.toNote
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.transactions.*
import java.util.*

class NoteRepositoryExposed : NoteRepository {
    override suspend fun getNoteList(): Either<RepositoryFailure, List<Note>> {
        return Either.catch {
            return either {
                val notes = transaction { NoteTableEntity.all().map { it.toNote() } }
                ensureNotNull(notes) { RepositoryFailure.NotFound }
            }
        }.mapLeft {
            RepositoryFailure.DatabaseError(it)
        }
    }

    override suspend fun getNoteById(id: UUID): Either<RepositoryFailure, Note> {
        return Either.catch {
            return either {
                val note =
                    transaction {
                        NoteTableEntity.findById(id)
                    }
                ensureNotNull(note) { RepositoryFailure.NotFound }
                note.toNote()
            }
        }.mapLeft {
            RepositoryFailure.DatabaseError(it)
        }
    }

    override suspend fun createNote(note: Note): Either<RepositoryFailure, Note> {
        return Either.catch {
            return either {
                val newNote =
                    transaction {
                        NoteTableEntity.new(note.id) {
                            title = note.title
                            content = note.content
                            createdat = note.createdAt
                            updatedat = note.updatedAt
                        }
                    }
                ensureNotNull(newNote) { RepositoryFailure.UnknownError }
                newNote.toNote()
            }
        }.mapLeft {
            RepositoryFailure.DatabaseError(it)
        }
    }

    override suspend fun updateNote(note: Note): Either<RepositoryFailure, Note> {
        return Either.catch {
            return either {
                transaction {
                    val noteById = NoteTableEntity.findById(EntityID(note.id, NoteTable))!!
                    ensureNotNull(note) { RepositoryFailure.NotFound }
                    noteById.title = note.title
                    noteById.content = note.content
                    noteById.updatedat = note.updatedAt
                    noteById
                }.toNote()
            }
        }.mapLeft {
            RepositoryFailure.DatabaseError(it)
        }

    }

    override suspend fun deleteNote(id: UUID): Either<RepositoryFailure, Unit> {
        return Either.catch {
            return either {
                val noteById = transaction {
                    NoteTableEntity.findById(id)
                }
                ensureNotNull(noteById) { RepositoryFailure.NotFound }
                transaction { noteById.delete() }
                noteById.toNote()
            }
        }.mapLeft {
            RepositoryFailure.DatabaseError(it)
        }
    }
}