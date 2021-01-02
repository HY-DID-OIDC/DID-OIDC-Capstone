package com.capstone.didauthoidc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class DemoApplication

fun main(args: Array<String>) {

    runApplication<DemoApplication>(*args)
}
