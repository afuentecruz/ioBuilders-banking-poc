package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.usecase.wallet.WalletCreateUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wallets")
class WalletController(private val createWalletUseCase: WalletCreateUseCase) {

    @PostMapping(path = ["/{userId}/create"])
    fun createWallet(@PathVariable userId: Long): ResponseEntity<WalletResponse> =
        ResponseEntity.ok(createWalletUseCase.createUserWallet(userId))

    @GetMapping(path = ["/{userId}"])
    fun getUserWallets(@PathVariable userId: Long): ResponseEntity<Any> =
        ResponseEntity.ok("Not implemented yet")

}

