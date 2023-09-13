package com.company.user.config

import com.company.user.model.annotations.UserResponseStatus
import com.company.user.model.enums.ResponseCode
import com.company.user.model.exception.JwtException
import com.company.user.model.exception.RoleException
import com.company.user.model.exception.UserException
import com.company.user.model.response.ErrorDataResponse
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException::class)
    fun handleException(userException: UserException) : ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val errors = userException.data;

        return if(AnnotationUtils.findAnnotation(userException.javaClass, UserResponseStatus::class.java) != null) {
            val result: UserResponseStatus?  = AnnotationUtils.findAnnotation(userException.javaClass, UserResponseStatus::class.java)
            ResponseEntity(ErrorDataResponse(errors, result!!.responseCode, (if (userException.message == null) result.reason else userException.message)!!), result.httpCode)
        } else {
            ResponseEntity(ErrorDataResponse(errors, ResponseCode.CP1004, userException.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RoleException::class)
    fun handleException(roleException: RoleException) : ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val errors = roleException.data;

        return if(AnnotationUtils.findAnnotation(roleException.javaClass, UserResponseStatus::class.java) != null) {
            val result: UserResponseStatus?  = AnnotationUtils.findAnnotation(roleException.javaClass, UserResponseStatus::class.java)
            ResponseEntity(ErrorDataResponse(errors, result!!.responseCode, (if (roleException.message == null) result.reason else roleException.message)!!), result.httpCode)
        } else {
            ResponseEntity(ErrorDataResponse(errors, ResponseCode.CP1004, roleException.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JwtException::class)
    fun handleException(jwtException: JwtException) : ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val errors = jwtException.data;

        return if(AnnotationUtils.findAnnotation(jwtException.javaClass, UserResponseStatus::class.java) != null) {
            val result: UserResponseStatus?  = AnnotationUtils.findAnnotation(jwtException.javaClass, UserResponseStatus::class.java)
            ResponseEntity(ErrorDataResponse(errors, result!!.responseCode, (if (jwtException.message == null) result.reason else jwtException.message)!!), result.httpCode)
        } else {
            ResponseEntity(ErrorDataResponse(errors, ResponseCode.CP1004, jwtException.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}