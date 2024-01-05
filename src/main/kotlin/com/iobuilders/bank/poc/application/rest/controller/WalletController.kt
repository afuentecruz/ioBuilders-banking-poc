package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.request.wallet.WalletDepositRequest
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletBalanceResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.usecase.wallet.WalletCreateUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDepositUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDetailsUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/wallets")
class WalletController(
    private val createWalletUseCase: WalletCreateUseCase,
    private val walletDetailsUseCase: WalletDetailsUseCase,
    private val walletDepositUseCase: WalletDepositUseCase
) {

    @PostMapping(path = ["/create"])
    fun createWallet(principal: Principal): ResponseEntity<WalletResponse> =
        createWalletUseCase.createUserWallet(principal.name).let { ResponseEntity.ok(it) }

    @GetMapping()
    fun getUserWallets(principal: Principal): ResponseEntity<List<WalletResponse>> =
        walletDetailsUseCase.getUserWallets(principal.name).let { ResponseEntity.ok(it) }

    @PostMapping(path = ["/{walletId}/deposit"])
    fun depositWallet(
        @PathVariable walletId: Long,
        @Valid @RequestBody depositRequest: WalletDepositRequest
    ): ResponseEntity<WalletResponse> =
        walletDepositUseCase.deposit(walletId, depositRequest).let { ResponseEntity.ok(it) }

    @GetMapping(path = ["/{walletId}/balance"])
    fun walletBalance(
        principal: Principal,
        @PathVariable walletId: Long
    ): ResponseEntity<WalletBalanceResponse> =
        walletDetailsUseCase.getWalletBalance(principal.name, walletId).let { ResponseEntity.ok(it) }
}

