package com.iobuilders.bank.poc.application.rest.response.wallet

import com.iobuilders.bank.poc.domain.Wallet

data class WalletResponse(
    val balance: Float, val currency: String
) {
    companion object
}

fun WalletResponse.Companion.toResponse(wallet: Wallet) =
    WalletResponse(balance = wallet.balance.amount, currency = wallet.balance.currency.name)
