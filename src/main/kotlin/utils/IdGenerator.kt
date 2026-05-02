package com.example.utils

import java.time.Instant
import java.util.UUID

object IdUtils {

    fun generateId(): String {
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val timestamp = Instant.now().toEpochMilli()

        return "${timestamp}_$uuid"
    }
}