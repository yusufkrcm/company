package com.company.user.controller

import com.company.user.model.request.LoginRequest
import com.company.user.model.request.RegisterRequest
import com.company.user.model.request.ValidateRequest
import com.company.user.model.response.LoginResponse
import com.company.user.model.response.ValidateResponse
import com.company.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        println("login")
        return ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Unit> {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/validate")
    fun validate(@RequestBody request: ValidateRequest): ResponseEntity<ValidateResponse> {
        return ResponseEntity.ok(authService.validate(request))
    }


}