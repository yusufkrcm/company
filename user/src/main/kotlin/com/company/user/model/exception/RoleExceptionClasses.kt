package com.company.user.model.exception

import com.company.user.model.annotations.UserResponseStatus
import com.company.user.model.enums.ResponseCode
import org.springframework.http.HttpStatus

open class RoleException(open val data: Map<String, String>? = null) : RuntimeException()

@UserResponseStatus(httpCode = HttpStatus.NOT_FOUND, reason = "Role not found", responseCode = ResponseCode.CP1005)
data class RoleNotFound(override val message: String?= null, override val data: Map<String, String>? = null) : RoleException()