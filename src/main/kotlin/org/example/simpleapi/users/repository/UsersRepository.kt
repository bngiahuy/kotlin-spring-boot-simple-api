package org.example.simpleapi.users.repository
import org.example.simpleapi.users.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long>  {
    fun findByEmail(email: String?): Users?
}