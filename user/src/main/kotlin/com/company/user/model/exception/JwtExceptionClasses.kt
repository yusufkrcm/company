package com.company.user.model.exception

import com.company.user.model.annotations.UserResponseStatus
import com.company.user.model.enums.ResponseCode
import org.springframework.http.HttpStatus

open class JwtException(open val data: Map<String, String>? = null) : RuntimeException()

@UserResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "JWT not valid", responseCode = ResponseCode.CP1003)
data class JwtNotValid(override val message: String?= null, override val data: Map<String, String>? = null) : JwtException()