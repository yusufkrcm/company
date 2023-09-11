package com.company.employee.model.response

import com.company.employee.model.enums.ResponseCode
import lombok.Getter

@Getter
sealed class DataResponse<T> : Response {
    var data: T? = null

    constructor(data: T, responseCode: ResponseCode, message: String?) : super(message, responseCode) {
        this.data = data
    }

    constructor(data: T, code: ResponseCode) : super(code) {
        this.data = data
    }

    constructor(responseCode: ResponseCode, message: String) : super(message, responseCode)
}

class SuccessDataResponse<T> : DataResponse<T> {
    constructor(data: T, message: String) : super(data, getDefaultSuccessCode(), message)
    constructor(data: T) : super(data, getDefaultErrorCode())
}

class ErrorDataResponse<T> : DataResponse<T> {
    constructor(data: T, message: String) : super(data, getDefaultErrorCode(), message)
    constructor(data: T, responseCode: ResponseCode, message: String) : super(data, responseCode, message)
    constructor(responseCode: ResponseCode, message: String ) : super(responseCode, message)
    constructor(data: T) : super(data, getDefaultErrorCode())
}