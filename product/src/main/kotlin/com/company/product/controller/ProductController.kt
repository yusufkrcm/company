package com.company.product.controller

import com.company.product.client.EmployeeClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class ProductController {
    @Autowired
    private lateinit var employeeClient: EmployeeClient
    @GetMapping
    fun getProducts() {
        employeeClient.veriGetir()
    }
}