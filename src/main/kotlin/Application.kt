package com.example

import com.example.database.supabaseModule
import com.example.di.appModule
import com.example.helper.LoggingPlugin
import com.example.plugin.configurePlugin
import com.example.routes.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(supabaseModule)
        modules(appModule)
    }
    install(LoggingPlugin)
    configurePlugin()
    configureRouting()
}
