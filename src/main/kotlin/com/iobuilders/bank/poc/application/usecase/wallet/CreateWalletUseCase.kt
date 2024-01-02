package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.response.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.fromDomain
import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.service.UserService

class CreateWalletUseCase(private val userService: UserService) {

    fun createUserWallet(userId: Long): WalletResponse =
        userService.findUser(userId).let {
            return WalletResponse.fromDomain(
                Wallet(userId = userId, balance = Money(amount = 0F, MoneyCurrency.FIAT))
            )
        }

}
