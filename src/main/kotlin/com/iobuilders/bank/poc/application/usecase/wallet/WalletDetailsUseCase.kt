package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletBalanceResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.fromDomain
import com.iobuilders.bank.poc.domain.service.AmlValidationService
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.domain.service.WalletService

class WalletDetailsUseCase(
    private val walletService: WalletService,
    private val userService: UserService,
    private val amlValidationService: AmlValidationService
) {
    fun getUserWallets(username: String): List<WalletResponse> = userService.findUsername(username).let { user ->
        walletService.findUserWallets(user.id!!).map { WalletResponse.fromDomain(it) }
    }

    fun getWalletBalance(username: String, walletId: Long): WalletBalanceResponse =
        amlValidationService.checkWalletOwnership(username, walletId).run {
            walletService.findWallet(walletId).let {
                WalletBalanceResponse.fromDomain(it)
            }
        }
}
