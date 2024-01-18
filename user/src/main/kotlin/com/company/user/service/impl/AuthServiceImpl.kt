package com.company.user.service.impl

import com.company.user.model.entity.User
import com.company.user.model.exception.UserAlreadyExists
import com.company.user.model.exception.WrongPassword
import com.company.user.model.mapper.UserMapper
import com.company.user.model.request.LoginRequest
import com.company.user.model.request.RegisterRequest
import com.company.user.model.request.ValidateRequest
import com.company.user.model.response.LoginResponse
import com.company.user.service.AuthService
import com.company.user.service.RoleService
import com.company.user.service.UserService
import com.company.user.util.JwtUtil
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthServiceImpl(
    private val userService: UserService,
    private val roleService: RoleService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val userMapper: UserMapper
) : AuthService {

    private val userRole: String = "USER"

    override fun login(request: LoginRequest): LoginResponse {

        val user: User = userService.findByUsername(request.username)

        if (request.password == user.password) {
            val role: String = user.role!!.name
            return LoginResponse(jwtUtil.generateToken(user.id.toString(), role))
        } else {
            throw WrongPassword()
        }

    }

    override fun register(request: RegisterRequest) {
        val role = roleService.findRoleByName(this.userRole)
        val user = userMapper.toEntity(request)
        user.role = role
        try {
            userService.save(user)
        } catch (e: DataIntegrityViolationException) {
            throw UserAlreadyExists(e.message)
        }
    }

    override fun validate(request: ValidateRequest): Boolean {
        return jwtUtil.validateJwtToken(request.token)
    }

}