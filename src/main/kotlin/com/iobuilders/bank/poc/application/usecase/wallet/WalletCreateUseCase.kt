package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.toResponse
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.domain.service.WalletService

class WalletCreateUseCase(private val userService: UserService, private val walletService: WalletService) {
    fun createUserWallet(userId: Long): WalletResponse = userService.findUser(userId).let { user ->
        walletService.createWallet(user).run {
            WalletResponse.toResponse(this)
        }
    }
}
