package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id:String,
    val name:String,
    val email:String,
    val password:String,
)

@Serializable
data class UserRequest(
    val name:String,
    val email:String,
    val password:String,
)
