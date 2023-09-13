package com.company.user.model.annotations

import com.company.user.model.enums.ResponseCode
import org.springframework.http.HttpStatus

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class UserResponseStatus(
    val httpCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val reason: String,
    val responseCode: ResponseCode
)