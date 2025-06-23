package org.example.simpleapi.users.service.impl

import org.example.simpleapi.users.dto.UsersDto
import org.example.simpleapi.users.repository.UsersRepository
import org.example.simpleapi.users.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val usersRepository: UsersRepository
) : UserService {
    override fun findAll(): List<UsersDto> {
        return usersRepository.findAll()
            .filter { it -> it.role.name.lowercase() == "user" }
            .map { user ->
            UsersDto(
                id = user.id,
                email = user.email,
                role = user.role.name.lowercase()
            )
        }
    }

    override fun findById(id: Long): UsersDto? {
        return usersRepository.findByIdOrNull(id)?.let { user ->
            UsersDto(
                id = user.id,
                email = user.email,
                role = user.role.name.lowercase()
            )
        }
    }

    override fun findByEmail(email: String): UsersDto? {
        return usersRepository.findByEmail(email)?.let { user ->
            UsersDto(
                id = user.id,
                email = user.email,
                role = user.role.name.lowercase()
            )
        }
    }

    override fun deleteById(id: Long) {
        usersRepository.deleteById(id)
    }
}