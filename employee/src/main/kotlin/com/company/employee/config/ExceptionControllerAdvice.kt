package com.company.employee.config

import com.company.employee.model.annotations.EmployeeResponseStatus
import com.company.employee.model.enums.ResponseCode
import com.company.employee.model.exception.EmployeeException
import com.company.employee.model.exception.JwtException
import com.company.employee.model.response.ErrorDataResponse
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmployeeException::class)
    fun handleException(employeeException: EmployeeException) : ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val errors = employeeException.data;

        return if(AnnotationUtils.findAnnotation(employeeException.javaClass, EmployeeResponseStatus::class.java) != null) {
            val result: EmployeeResponseStatus?  = AnnotationUtils.findAnnotation(employeeException.javaClass, EmployeeResponseStatus::class.java)
            ResponseEntity(ErrorDataResponse(errors, result!!.responseCode, (if (employeeException.message == null) result.reason else employeeException.message)!!), result.httpCode)
        } else {
            ResponseEntity(ErrorDataResponse(errors, ResponseCode.CP1004, employeeException.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JwtException::class)
    fun handleException(jwtException: JwtException) : ResponseEntity<ErrorDataResponse<Map<String, String>?>> {
        val errors = jwtException.data;

        return if(AnnotationUtils.findAnnotation(jwtException.javaClass, EmployeeResponseStatus::class.java) != null) {
            val result: EmployeeResponseStatus?  = AnnotationUtils.findAnnotation(jwtException.javaClass, EmployeeResponseStatus::class.java)
            ResponseEntity(ErrorDataResponse(errors, result!!.responseCode, (if (jwtException.message == null) result.reason else jwtException.message)!!), result.httpCode)
        } else {
            ResponseEntity(ErrorDataResponse(errors, ResponseCode.CP1004, jwtException.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}