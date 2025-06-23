package org.example.simpleapi.users.service.impl

import org.example.simpleapi.common.JwtService
import org.example.simpleapi.users.dto.AuthDto
import org.example.simpleapi.users.entity.Users
import org.example.simpleapi.users.repository.UsersRepository
import org.example.simpleapi.users.service.AuthService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl (
    private val authenticationManager: AuthenticationManager,
    private val usersRepo: UsersRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
): AuthService {
    override fun login(email: String, password: String): AuthDto {
        val auth = usersRepo.findByEmail(email)
            ?: throw IllegalArgumentException("Invalid email or password")
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email, password)
        )
        val userDetails = authentication.principal as Users
        val jwt = jwtService.generateToken(userDetails)
        return AuthDto(
            id = userDetails.id,
            email = userDetails.email,
            token = jwt,
            role = userDetails.role.name
        )
    }

    override fun register(email: String, password: String): AuthDto {
        val existingAuth = usersRepo.findByEmail(email)
        if (existingAuth != null) {
            throw IllegalArgumentException("Email already registered")
        }
        val user = Users(
            email = email,
            passwordHash = passwordEncoder.encode(password),
            role = Users.UserRole.USER
        )
        val savedUser = usersRepo.save(user)
        val jwt = jwtService.generateToken(savedUser)
        return AuthDto(
            id = savedUser.id,
            email = savedUser.email,
            token = jwt,
            role = savedUser.role.name
        )
    }

}