package com.example

import com.example.database.configureSupabase
import com.example.plugin.configurePlugin
import com.example.routes.configureRouting
import io.ktor.server.application.Application

fun Application.module() {
    configurePlugin()
    val supabase = configureSupabase()
    configureRouting(supabase)
}