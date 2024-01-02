package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.response.WalletResponse
import com.iobuilders.bank.poc.application.usecase.wallet.CreateWalletUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/wallets")
class WalletController(private val createWalletUseCase: CreateWalletUseCase) {

    @PostMapping(path = ["/{userId}/create"])
    fun createWallet(@PathVariable userId: Long): ResponseEntity<WalletResponse> =
        ResponseEntity.ok(createWalletUseCase.createUserWallet(userId))

}
