package com.example

import com.example.database.supabaseModule
import com.example.di.appModule
import com.example.helper.LoggingPlugin
import com.example.plugin.configurePlugin
import com.example.plugin.configureSecurity
import com.example.routes.configureRouting
import io.ktor.server.application.*
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(supabaseModule, appModule)
    }

    install(LoggingPlugin)

    configurePlugin()
    configureSecurity()
    configureRouting()
}

//adding cors  implementation("io.ktor:ktor-server-cors:$ktor_version")