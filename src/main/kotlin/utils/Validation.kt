package com.example.utils

private val emailRegex =
    Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && emailRegex.matches(this)
}
fun String.isValidPassword(): Boolean {
    return this.length >= 8 &&
            any { it.isUpperCase() } &&
            any { it.isLowerCase() } &&
            any { it.isDigit() }
}