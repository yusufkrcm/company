package com.company.user.service

import com.company.user.model.entity.User

interface UserService {

    fun findByUsername(username: String): User?

    fun save(user: User): User
}