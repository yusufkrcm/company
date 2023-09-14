package com.company.user.model.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:NotBlank(message = "Username is required")
    @field:Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    val username: String?,
    @field:NotBlank(message = "Password is required")
    val password: String?,
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email is not valid")
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    @field:NotBlank(message = "Phone number is required")
    val phoneNumber: String?
)