package com.purplemango.gms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
class GmsKotlinApp

    fun main(args: Array<String>) {
        runApplication<GmsKotlinApp>(*args)
    }

    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
