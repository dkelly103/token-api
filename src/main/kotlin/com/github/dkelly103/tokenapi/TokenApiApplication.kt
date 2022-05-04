package com.github.dkelly103.tokenapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication
class TokenApiApplication

fun main(args: Array<String>) {
	runApplication<TokenApiApplication>(*args)
}