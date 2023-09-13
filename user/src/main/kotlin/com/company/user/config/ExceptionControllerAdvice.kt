package com.company.user.config

import com.company.user.model.annotations.UserResponseStatus
import com.company.user.model.enums.ResponseCode
import com.company.user.model.exception.UserException
import com.company.user.model.exception.UserValidationException
import com.company.user.model.response.ErrorDataResponse
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException::class)
    fun handleException(userException: UserException): ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val errors = userException.data;

        return if (AnnotationUtils.findAnnotation(userException.javaClass, UserResponseStatus::class.java) != null) {
            val result: UserResponseStatus? =
                AnnotationUtils.findAnnotation(userException.javaClass, UserResponseStatus::class.java)
            ResponseEntity(
                ErrorDataResponse(
                    errors,
                    result!!.responseCode,
                    (if (userException.message == null) result.reason else userException.message)!!
                ), result.httpCode
            )
        } else {
            ResponseEntity(
                ErrorDataResponse(errors, ResponseCode.CP1004, userException.message!!),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val message = exception.allErrors.map { it.defaultMessage }.joinToString()
        return handleException(UserValidationException(message = message))
    }

}