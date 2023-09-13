package com.company.user.service.impl

import com.company.user.model.entity.User
import com.company.user.model.exception.UserNotFound
import com.company.user.repository.UserRepository
import com.company.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun findByUsername(username: String): User = userRepository.findByUsername(username)
        .orElseThrow { UserNotFound() }

    override fun save(user: User): User = userRepository.save(user)

}