package org.example.simpleapi.users.models

data class LoginRequest(
    val email: String,
    val password: String
)
