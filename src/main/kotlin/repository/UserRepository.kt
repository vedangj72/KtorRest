package com.example.repository

import com.example.core.ApiHandler
import com.example.models.User


class UserRepository(val apiHandler: ApiHandler) {
    suspend fun addUser(user: User): User {
        return apiHandler.post(
            "user",
            body = user,
        )
    }
    suspend fun findByEmail(email: String): User? {

        val result = apiHandler.get<User>(

            table = "user",

            column = "email",

            value = email

        )

        return result.firstOrNull()

    }
    suspend fun getAllUsers(): List<User> {
        return apiHandler.getAll("user")
    }
}