package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.toResponse
import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.service.UserService

class WalletCreateUseCase(private val userService: UserService) {

    fun createUserWallet(userId: Long): WalletResponse =
        userService.findUser(userId).let {
            return WalletResponse.toResponse(
                Wallet(userId = userId, balance = Money(amount = 0F, MoneyCurrency.FIAT))
            )
        }

}
