package com.vini.torres.ecommerceAPI.presentation.controller

import com.vini.torres.ecommerceAPI.application.usecase.user.CreateUserUseCase
import com.vini.torres.ecommerceAPI.application.usecase.user.DeleteUserUseCase
import com.vini.torres.ecommerceAPI.application.usecase.user.GetUserUseCase
import com.vini.torres.ecommerceAPI.application.usecase.user.UpdateUserUseCase
import com.vini.torres.ecommerceAPI.presentation.request.UserRequest
import com.vini.torres.ecommerceAPI.presentation.response.UserResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) {

    @PostMapping
    fun createUser(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val createdUser = createUserUseCase.execute(request.toDomain())
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromDomain(createdUser))
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val users = getUserUseCase.getAll().map { UserResponse.fromDomain(it) }
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
        return try {
            val user = getUserUseCase.getById(id)
            ResponseEntity.ok(UserResponse.fromDomain(user))
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        return try {
            val updatedUser = updateUserUseCase.execute(id, request.toDomain())
            ResponseEntity.ok(UserResponse.fromDomain(updatedUser))
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            deleteUserUseCase.execute(id)
            ResponseEntity.noContent().build()
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }
}
