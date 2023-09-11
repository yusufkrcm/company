package com.company.user.model.request

data class RegisterRequest (
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)