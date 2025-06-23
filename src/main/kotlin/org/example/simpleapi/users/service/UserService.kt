package org.example.simpleapi.users.service

import org.example.simpleapi.users.dto.UsersDto
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun findAll(): List<UsersDto>
    fun findById(id: Long): UsersDto?
    fun findByEmail(email: String): UsersDto?
    fun deleteById(id: Long)
}