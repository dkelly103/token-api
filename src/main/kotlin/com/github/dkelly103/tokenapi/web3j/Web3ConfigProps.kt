package com.github.dkelly103.tokenapi.web3j

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "web3")
class Web3ConfigProps {

    var url: String = "http://localhost:8545"
    var chainId: Long = 1
    var privateKey: String? = null
    var pollingAttempts: Int = 100
    var pollingInterval: Long = 100


}