package com.example.models


//for success
data class Response<T>(
    val data: T?,
    val status: Int?,
    val message: String?,
)

