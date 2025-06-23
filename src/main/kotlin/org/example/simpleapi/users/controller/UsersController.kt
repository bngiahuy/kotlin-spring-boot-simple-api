package org.example.simpleapi.users.controller
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.example.simpleapi.common.models.ApiResponse
import org.example.simpleapi.users.dto.UsersDto
import org.example.simpleapi.users.entity.Users
import org.example.simpleapi.users.repository.UsersRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "bearerAuth") // Đảm bảo rằng các endpoint này yêu cầu xác thực JWT
@RequestMapping("/api/v1/users")
class UsersController(private val usersRepository: UsersRepository) {
    private val logger = org.slf4j.LoggerFactory.getLogger(UsersController::class.java)
    @GetMapping
    fun getAllUsers(): ResponseEntity<ApiResponse<List<UsersDto>>> {
        val data = usersRepository.findAll().map { entity -> entity.toDto()}
        logger.info("API - Users - Retrieved all users: ${data.size} users found")
        return ResponseEntity.ok(ApiResponse(data))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<ApiResponse<UsersDto>>  {
        return usersRepository.findById(id)
            .map { user -> ResponseEntity.ok(ApiResponse(user.toDto()))}
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createUser(@RequestBody user: Users): ResponseEntity<ApiResponse<UsersDto>> {
        val savedUser = usersRepository.save(user).toDto()
        logger.info("API - Users - Created new user: ${savedUser.id} with email: ${savedUser.email}")
        return ResponseEntity.status(201).body(ApiResponse(savedUser))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: Users): ResponseEntity<ApiResponse<UsersDto>>  {
        return usersRepository.findById(id)
            .map { existingUser ->
                val userToUpdate = existingUser.copy(
                    email = updatedUser.email
                )
                logger.info("API - Users - Updated user: ${userToUpdate.id} with email: ${userToUpdate.email}")
                ResponseEntity.ok(ApiResponse(usersRepository.save(userToUpdate).toDto()))
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        usersRepository.findById(id).ifPresent { user ->
            logger.info("API - Users - Deleting user: ${user.id} with email: ${user.email}")
            usersRepository.delete(user)
        }
        return ResponseEntity.noContent().build()
    }
}