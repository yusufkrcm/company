package com.company.product.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "employee", url = "http://localhost:8081")
interface EmployeeClient {
    @GetMapping("employee")
    fun veriGetir(): String?
}