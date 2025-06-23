package org.example.simpleapi.users.service

import org.example.simpleapi.users.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService (
    private val usersRepository: UsersRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return usersRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found: $username")
    }
}