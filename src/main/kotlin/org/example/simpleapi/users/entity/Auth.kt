package org.example.simpleapi.users.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.simpleapi.users.dto.AuthDto

@Entity
data class Auth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String? = null,
    val password: String? = null,
    val token: String? = null,
    val role: String? = null
) {
    fun toDto(): AuthDto {
        return AuthDto(
            id = id,
            email = email,
            token = token,
            role = role
        )
    }
}
