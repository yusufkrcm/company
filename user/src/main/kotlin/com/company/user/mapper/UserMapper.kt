package com.company.user.mapper

import com.company.user.model.entity.Role
import com.company.user.model.entity.User
import com.company.user.model.request.RegisterRequest

fun RegisterRequest.toEntity(role: Role): User {
    return User(
        username = this.username!!,
        firstName = this.firstName!!,
        lastName = this.lastName!!,
        password = this.password!!,
        email = this.email!!,
        phoneNumber = this.phoneNumber!!,
        role = role
    )
}