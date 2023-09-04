package com.company.product.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient
interface EmployeeClient {
    @GetMapping("/api/endpoint2")
    fun  // Mikroservis2 tarafındaki istenen API endpointini belirtin
            veriGetir(): String? // Yanıt tipini uygun şekilde ayarlayın
}