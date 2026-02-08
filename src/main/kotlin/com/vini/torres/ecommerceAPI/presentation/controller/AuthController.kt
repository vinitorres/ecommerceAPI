package com.vini.torres.ecommerceAPI.presentation.controller

import com.vini.torres.ecommerceAPI.application.usecase.auth.LoginUseCase
import com.vini.torres.ecommerceAPI.presentation.request.LoginRequest
import com.vini.torres.ecommerceAPI.presentation.response.LoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val loginUseCase: LoginUseCase) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(loginUseCase.execute(request))
    }
}
