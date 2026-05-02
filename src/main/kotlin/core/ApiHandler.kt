package com.example.core

import com.example.helper.AppException
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.ktor.http.HttpStatusCode
import java.io.IOException

class ApiHandler(val client: SupabaseClient) {

    suspend inline fun <T> execute(
        crossinline block: suspend () -> T
    ): T {
        return try {
            block()
        } catch (e: Exception) {
            throw mapToAppException(e)
        }
    }

    fun mapToAppException(e: Exception): AppException {

        return when (e) {
            is AppException -> e

            is IOException ->
                AppException(
                    HttpStatusCode.ServiceUnavailable,
                    "Network error, please try again"
                )
            is io.ktor.client.network.sockets.SocketTimeoutException ->
                AppException(
                    HttpStatusCode.GatewayTimeout,
                    "Request timeout"
                )

            else ->
                AppException(
                    HttpStatusCode.InternalServerError,
                    e.message ?: "Something went wrong"
                )
        }
    }

    suspend inline fun <reified T : Any> getById(
        table: String,
        id: String
    ): T? = execute {
        client.from(table)
            .select {
                filter { eq("id", id) }
            }
            .decodeSingleOrNull<T>()
    }

    suspend inline fun <reified T : Any> post(
        table: String,
        body: T
    ): T = execute {
        client.from(table)
            .insert(body)

        body
    }
}
