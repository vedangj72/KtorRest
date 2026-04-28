package com.example

import com.example.database.supabaseModule
import com.example.di.appModule
import com.example.plugin.configurePlugin
import com.example.routes.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.module() {

    install(Koin) {
        modules(supabaseModule)
        modules(appModule)
    }

    configurePlugin()
    configureRouting()
}