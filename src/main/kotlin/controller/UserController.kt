package com.example.controller

import com.example.models.User
import com.example.repository.UserRepository
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class UserController(
    private val repo: UserRepository
) {

    suspend fun addUser(call: ApplicationCall) {
        val user = call.receive<User>()

        val createdUser = repo.addUser(user)

        call.respond(createdUser)
    }
}