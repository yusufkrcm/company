package com.company.user.model.exception

import com.company.user.model.annotations.UserResponseStatus
import com.company.user.model.enums.ResponseCode
import org.springframework.http.HttpStatus

open class UserException(open val data: Map<String, String>? = null) : RuntimeException()

@UserResponseStatus(httpCode = HttpStatus.NOT_FOUND, reason = "User not found", responseCode = ResponseCode.CP1005)
data class UserNotFound(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()

@UserResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "User already exists", responseCode = ResponseCode.CP1002)
data class UserAlreadyExists(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()

@UserResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "Wrong password", responseCode = ResponseCode.CP1002)
data class WrongPassword(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()

@UserResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "JWT not valid", responseCode = ResponseCode.CP1003)
data class JwtNotValid(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()

@UserResponseStatus(httpCode = HttpStatus.NOT_FOUND, reason = "Role not found", responseCode = ResponseCode.CP1005)
data class RoleNotFound(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()

@UserResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "Validation error", responseCode = ResponseCode.CP1002)
data class UserValidationException(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()

@UserResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "Mapping error", responseCode = ResponseCode.CP1004)
data class MapperException(override val message: String?= null, override val data: Map<String, String>? = null) : UserException()
