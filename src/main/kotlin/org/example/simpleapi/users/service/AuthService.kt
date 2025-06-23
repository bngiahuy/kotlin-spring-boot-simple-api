package org.example.simpleapi.users.service

import org.example.simpleapi.users.dto.AuthDto
import org.springframework.stereotype.Service

@Service
interface AuthService {
    fun login(email: String, password: String): AuthDto
    fun register(email: String, password: String): AuthDto
}