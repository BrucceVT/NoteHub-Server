package com.example.features.todoManagment.infraestruture.utils.db

import com.example.config.*

class DatabaseConfig(private val config: AppConfig) {

    val user = config.appConfig.property("database.user").getString()
    val pass = config.appConfig.property("database.password").getString()
    val host = config.appConfig.property("database.host").getString()
    val port = config.appConfig.property("database.port").getString()
    val name = config.appConfig.property("database.nameDB").getString()
    val driver = config.appConfig.property("database.driver").getString()

    fun getJdbcUrl(): String {
        return "jdbc:mysql://$host:$port/$name"
    }
}
