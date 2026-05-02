package com.example.controller

import com.example.helper.BadRequestException
import com.example.models.User
import com.example.models.successResponse
import com.example.repository.UserRepository
import com.example.utils.isValidEmail
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class UserController(
    private val repo: UserRepository
) {

    suspend fun addUser(call: ApplicationCall) {
        val user = call.receive<User>()
        if (!user.email.isValidEmail()) {
            throw BadRequestException("Invalid email format")
        }
        val createdUser = repo.addUser(user)

        call.respond(
            status = HttpStatusCode.Created,
            message = successResponse(
                data = createdUser,
                status = HttpStatusCode.Created.value,
                message = "User created successfully"
            )
        )
    }
}
