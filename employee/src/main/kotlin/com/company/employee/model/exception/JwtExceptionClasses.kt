package com.company.employee.model.exception

import com.company.employee.model.annotations.EmployeeResponseStatus
import com.company.employee.model.enums.ResponseCode
import org.springframework.http.HttpStatus

open class JwtException(open val data: Map<String, String>? = null) : RuntimeException()

@EmployeeResponseStatus(httpCode = HttpStatus.BAD_REQUEST, reason = "JWT not valid", responseCode = ResponseCode.CP1003)
data class JwtNotValid(override val message: String?= null, override val data: Map<String, String>? = null) : JwtException()