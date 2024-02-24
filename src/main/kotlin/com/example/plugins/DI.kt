package com.example.plugins

import com.example.features.todoManagment.di.noteModule
import com.example.features.todoManagment.infraestruture.di.dbModule
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.*
import org.koin.ktor.plugin.*
import org.koin.logger.*

fun Application.configureDI() {

    val globalModule =
        module {
            single<ApplicationConfig> { this@configureDI.environment.config }
        }

    install(Koin) {
        slf4jLogger()
        modules(globalModule, dbModule, noteModule)
    }
}