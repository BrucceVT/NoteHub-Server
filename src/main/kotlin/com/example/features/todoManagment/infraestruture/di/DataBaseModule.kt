package com.example.features.todoManagment.infraestruture.di

import com.example.config.*
import com.example.features.todoManagment.infraestruture.utils.db.DatabaseConfig
import org.koin.dsl.*

val dbModule = module {
    single { AppConfig() }
    single { DatabaseConfig(get()) }}
