package com.company.user.model.enums

import lombok.Getter
import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
@Getter
enum class ResponseCode(val explanation: String) {
    CP1001("Success Code"),
    CP1002("Bad Request Code"),
    CP1003("Invalid Token Code"),
    CP1004("Internal Server Error Code"),
    CP1005("Not Found Code")
}