package com.example.routes

import io.github.jan.supabase.SupabaseClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
data class PostRequest(
    val name: String
)

fun Application.configureRouting() {
    val service by inject<SupabaseClient>()
    routing {
        healthCheck()
        userRoute()
    }
}
