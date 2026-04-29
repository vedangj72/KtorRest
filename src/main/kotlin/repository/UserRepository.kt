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
}