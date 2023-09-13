package com.company.user.service

import com.company.user.model.request.LoginRequest
import com.company.user.model.request.RegisterRequest
import com.company.user.model.request.ValidateRequest
import com.company.user.model.response.LoginResponse


interface AuthService {
    fun login(request: LoginRequest): LoginResponse
    fun register(request: RegisterRequest)
    fun validate(request: ValidateRequest) : Boolean
}