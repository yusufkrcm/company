package com.company.user.controller

import com.company.user.model.request.LoginRequest
import com.company.user.model.request.RegisterRequest
import com.company.user.model.request.ValidateRequest
import com.company.user.model.response.LoginResponse
import com.company.user.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid request: RegisterRequest): ResponseEntity<Unit> {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/validate")
    fun validate(@RequestBody request: ValidateRequest): ResponseEntity<Boolean> {
        return ResponseEntity.ok(authService.validate(request))
    }


}