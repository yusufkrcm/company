package com.company.apigw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ApigwApplication

fun main(args: Array<String>) {
	runApplication<ApigwApplication>(*args)

}
