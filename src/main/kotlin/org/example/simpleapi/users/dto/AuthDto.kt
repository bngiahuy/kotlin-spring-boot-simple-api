package org.example.simpleapi.users.dto

data class RegisterRequest(
    val password: String,
    val email: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String,
)

data class AuthDto(
    val id: Long?,
    val email: String?,
    val token: String?,
    val role: String?
)