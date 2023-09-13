package com.company.employee.service

import com.company.employee.model.exception.EmployeeNotFound
import org.springframework.stereotype.Service

@Service
class asdsService {
    fun asd() {
        throw EmployeeNotFound()
    }
}