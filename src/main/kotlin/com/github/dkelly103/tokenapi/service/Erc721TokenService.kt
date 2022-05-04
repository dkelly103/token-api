package com.github.dkelly103.tokenapi.service

import com.github.dkelly103.tokenapi.web3j.Token
import org.springframework.stereotype.Service

@Service
class Erc721TokenService (private val token: Token) {

    fun getToken(id: String): String {
        return token.contractAddress
    }

}