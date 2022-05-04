package com.github.dkelly103.tokenapi.controller

import com.github.dkelly103.tokenapi.service.Erc721TokenService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/token/erc721")
class Erc721TokenController(private val tokenService: Erc721TokenService) {

    @GetMapping("{id}")
    fun getToken(@PathVariable id: String): String {
        return tokenService.getToken(id)
    }

}