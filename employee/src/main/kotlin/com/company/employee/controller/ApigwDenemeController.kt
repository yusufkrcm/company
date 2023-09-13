package com.company.employee.controller

import com.company.employee.service.asdsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employee")
class ApigwDenemeController {

    @Autowired
    private lateinit var asdsService: asdsService;

    @GetMapping
    fun getAnything() {
        println("Selam ben geldim.")
        asdsService.asd()
    }
}