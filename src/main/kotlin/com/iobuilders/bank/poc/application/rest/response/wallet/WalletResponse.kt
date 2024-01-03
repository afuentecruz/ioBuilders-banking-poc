package com.iobuilders.bank.poc.application.rest.response.wallet

import com.iobuilders.bank.poc.domain.Wallet

data class WalletResponse(
    val id: Long, val balance: Float, val currency: String
) {
    companion object
}

fun WalletResponse.Companion.toResponse(wallet: Wallet) =
    WalletResponse(id = wallet.id!!, balance = wallet.balance.amount, currency = wallet.balance.currency.name)
