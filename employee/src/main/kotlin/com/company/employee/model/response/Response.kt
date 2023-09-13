package com.company.employee.model.response

import com.company.employee.model.enums.ResponseCode
import lombok.AllArgsConstructor

@AllArgsConstructor
sealed class Response (
    var message: String? = null,
    var responseCode: ResponseCode? = null
) {
    constructor(responseCode: ResponseCode) : this() { this.responseCode = responseCode}

    companion object {
        fun getDefaultSuccessCode() : ResponseCode = ResponseCode.CP1001
        fun getDefaultErrorCode() : ResponseCode = ResponseCode.CP1004
    }
}