package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.request.wallet.WalletDepositRequest
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.usecase.wallet.WalletCreateUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDepositUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDetailsUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wallets")
class WalletController(
    private val createWalletUseCase: WalletCreateUseCase,
    private val walletDetailsUseCase: WalletDetailsUseCase,
    private val walletDepositUseCase: WalletDepositUseCase
) {

    @PostMapping(path = ["/{userId}/create"])
    fun createWallet(@PathVariable userId: Long): ResponseEntity<WalletResponse> =
        createWalletUseCase.createUserWallet(userId).let { ResponseEntity.ok(it) }

    @GetMapping(path = ["/{userId}"])
    fun getUserWallets(@PathVariable userId: Long): ResponseEntity<List<WalletResponse>> =
        walletDetailsUseCase.getUserWallets(userId).let { ResponseEntity.ok(it) }

    @PostMapping(path = ["/{walletId}/deposit"])
    fun depositWallet(
        @PathVariable walletId: Long,
        @Valid @RequestBody depositRequest: WalletDepositRequest
    ): ResponseEntity<WalletResponse> =
        walletDepositUseCase.deposit(walletId, depositRequest).let { ResponseEntity.ok(it) }

}

