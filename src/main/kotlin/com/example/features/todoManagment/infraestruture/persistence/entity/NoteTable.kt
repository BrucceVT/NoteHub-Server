package com.example.features.todoManagment.infraestruture.persistence.entity

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.*
import java.util.*

object NoteTable : IdTable<UUID>() {
    override val id: Column<EntityID<UUID>> = uuid("id").entityId()
    val title = varchar("title", 100)
    val content = text("content")
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}

class NoteTableEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<NoteTableEntity>(NoteTable)

    var title by NoteTable.title
    var content by NoteTable.content
    var createdat by NoteTable.createdAt
    var updatedat by NoteTable.updatedAt

}