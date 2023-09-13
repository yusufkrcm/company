package com.company.user.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class LoginRequest(
    @field:NotBlank(message = "Username is required")
    @field:Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    val username: String,
    @field:NotEmpty(message = "Password is required")
    val password: String
)
