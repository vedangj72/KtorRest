package com.example.helper

import io.ktor.server.application.createRouteScopedPlugin

val LoggingPlugin = createRouteScopedPlugin("Logging") {
    onCall { call ->
        println("Incoming request")
    }
    onCallReceive { req ->
        println("Request body $req")
    }
}