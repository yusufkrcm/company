package com.company.employee.model.annotations

import com.company.employee.model.enums.ResponseCode
import org.springframework.http.HttpStatus

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class EmployeeResponseStatus(
    val httpCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val reason: String,
    val responseCode: ResponseCode
)