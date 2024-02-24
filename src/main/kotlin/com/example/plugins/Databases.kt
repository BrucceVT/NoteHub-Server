package com.example.plugins

import com.example.features.todoManagment.infraestruture.persistence.entity.*
import com.example.features.todoManagment.infraestruture.utils.db.DatabaseConfig
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Application.configureDatabases() {

    val databaseConfig: DatabaseConfig by inject()
    val tables = arrayOf(NoteTable)

    Database.connect(
        url = databaseConfig.getJdbcUrl(),
        user = databaseConfig.user,
        driver = databaseConfig.driver,
        password = databaseConfig.pass
    )
    transaction {
        SchemaUtils.createMissingTablesAndColumns(*tables)

    }
}
