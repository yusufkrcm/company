package com.company.employee.model.exception

import com.company.employee.model.annotations.EmployeeResponseStatus
import com.company.employee.model.enums.ResponseCode
import org.springframework.http.HttpStatus

open class EmployeeException(open val data: Map<String, String>? = null) : RuntimeException()

@EmployeeResponseStatus(httpCode = HttpStatus.NOT_FOUND, reason = "Employee not found", responseCode = ResponseCode.CP1005)
data class EmployeeNotFound(override val message: String?= null, override val data: Map<String, String>? = null) : EmployeeException()

