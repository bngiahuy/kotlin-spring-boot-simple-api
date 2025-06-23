package org.example.simpleapi.users.controller

import jakarta.servlet.http.HttpServletResponse
import org.example.simpleapi.common.models.ApiResponse
import org.example.simpleapi.users.dto.AuthDto
import org.example.simpleapi.users.dto.AuthResponse
import org.example.simpleapi.users.dto.RegisterRequest
import org.example.simpleapi.users.models.LoginRequest
import org.example.simpleapi.users.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController @Autowired constructor(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(
        response: HttpServletResponse,
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        try {
            val authDto = authService.login(loginRequest.email, loginRequest.password)
            val authResponse = AuthResponse(token = authDto.token ?: "")
            return ResponseEntity.ok(ApiResponse(authResponse))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ApiResponse(false, e.message))
        }
    }

    @PostMapping("/register")
    fun register(
        @RequestBody registerRequest: RegisterRequest
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        try {
            val authDto = authService.register(registerRequest.email, registerRequest.password)
            val authResponse = AuthResponse(token = authDto.token ?: "")
            return ResponseEntity.ok(ApiResponse(authResponse))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ApiResponse(false, e.message))
        }
    }
}