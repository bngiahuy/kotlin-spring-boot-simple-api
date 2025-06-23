package org.example.simpleapi.users.controller
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.example.simpleapi.common.models.ApiResponse
import org.example.simpleapi.users.dto.UsersDto
import org.example.simpleapi.users.repository.UsersRepository
import org.example.simpleapi.users.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.example.simpleapi.users.entity.Users as Users1

@RestController
@SecurityRequirement(name = "bearerAuth") // Đảm bảo rằng các endpoint này yêu cầu xác thực JWT
@RequestMapping("/api/v1/users")
class UsersController(private val userService: UserService) {
    private val logger = org.slf4j.LoggerFactory.getLogger(UsersController::class.java)
    @GetMapping
    fun getAllUsers(): ResponseEntity<ApiResponse<List<UsersDto>>> {
        val data = userService.findAll()
        logger.info("API - GET Users - Retrieved all users: ${data.size} users found")
        return ResponseEntity.ok(ApiResponse(data))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<ApiResponse<UsersDto>>  {
        val user = userService.findById(id)
        return if (user != null) {
            logger.info("API - Users - Retrieved user by ID: $id")
            ResponseEntity.ok(ApiResponse(user))
        } else {
            logger.warn("API - GET Users - User not found with ID: $id")
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        val user = userService.findById(id)
        if (user == null) {
            logger.warn("API - DEL Users - User not found with ID: $id")
            return ResponseEntity.notFound().build()
        }
        userService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}