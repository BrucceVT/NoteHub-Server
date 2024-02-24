package com.example.config

import com.typesafe.config.*
import io.ktor.server.config.*

class AppConfig {
    val appConfig: HoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load())
}