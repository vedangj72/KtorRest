package com.example

import com.example.plugin.configurePlugin
import com.example.routes.configureRouting
import io.ktor.server.application.Application

fun Application.module() {
    configurePlugin()
    configureRouting()
}