package com.company.user.service.impl

import com.company.user.model.request.LoginRequest
import com.company.user.model.request.RegisterRequest
import com.company.user.model.request.ValidateRequest
import com.company.user.model.response.LoginResponse
import com.company.user.model.response.ValidateResponse
import com.company.user.model.entity.User
import com.company.user.service.AuthService
import com.company.user.service.RoleService
import com.company.user.service.UserService
import com.company.user.util.JwtUtil
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.SQLException


@Service
class AuthServiceImpl(
    private val userService: UserService,
    private val roleService: RoleService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) : AuthService {

    override fun login(request: LoginRequest): LoginResponse {

        val user: User = userService.findByUsername(request.username) ?: throw Exception("User not found")

        if (passwordEncoder.matches(request.password, user.password)) {
            val role: String = user.role?.name ?: "NONE"
            return LoginResponse(jwtUtil.generateToken(user.username, role))
        } else {
            throw Exception("Wrong password")
        }

    }

    override fun register(request: RegisterRequest) {

        try {
            userService.save(
                User(
                    username = request.username,
                    password = passwordEncoder.encode(request.password),
                    email = request.email,
                    firstName = request.firstName,
                    lastName = request.lastName,
                    phoneNumber = request.phoneNumber,
                    role = roleService.findRoleByName("USER")
                )
            )
        } catch (e: DataIntegrityViolationException) {
            throw SQLException("This user already exists: " + request.username)
        }

    }

    override fun validate(request: ValidateRequest): ValidateResponse {
        jwtUtil.validateJwtToken(request.token)
        val username: String = jwtUtil.getSubject(request.token)
        val role: String = jwtUtil.getClaim(request.token, "role")
        return ValidateResponse(username, role)
    }

}