package com.company.employee.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApigwDenemeController {
    @GetMapping
    fun getAnything() {
        println("Selam ben geldim.")
    }
}