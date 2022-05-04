package com.github.dkelly103.tokenapi.web3j

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.Web3jService
import org.web3j.protocol.besu.Besu
import org.web3j.protocol.besu.JsonRpc2_0Besu
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.tx.gas.DefaultGasProvider

@Configuration
class Web3jConfig (private val config: Web3ConfigProps) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Bean
    fun web3j(): Web3j {
        return Web3j.build(HttpService(config.url))
    }

    @Bean
    fun token(web3j: Web3j): Token {

        val credentials: Credentials = Credentials.create(config.privateKey)
        val contractGasProvider: ContractGasProvider = DefaultGasProvider()
        val transactionManager: TransactionManager = RawTransactionManager(
            web3j,
            credentials,
            config.chainId,
            config.pollingAttempts,
            config.pollingInterval)

        val contract = Token.deploy(
            web3j,
            transactionManager,
            contractGasProvider,
            "name", "symbol", "baseUri").send()

        log.info("Contract address: ${contract.contractAddress}")

        return contract
    }

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

}