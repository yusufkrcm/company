package com.company.employee.model.response

import com.company.employee.model.enums.ResponseCode
import lombok.AllArgsConstructor

@AllArgsConstructor
sealed class Response (
    private var message: String? = null,
    private var responseCode: ResponseCode? = null
) {
    constructor(responseCode: ResponseCode) : this() { this.responseCode = responseCode}

    companion object {
        fun getDefaultSuccessCode() : ResponseCode = ResponseCode.CP1001
        fun getDefaultErrorCode() : ResponseCode = ResponseCode.CP1004
    }
}