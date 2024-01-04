package com.iobuilders.bank.poc.application.rest.response.wallet

import com.iobuilders.bank.poc.domain.Wallet
import java.math.BigDecimal

data class WalletBalanceResponse(
    val balance: BigDecimal, val currency: String
) {
    companion object
}

fun WalletBalanceResponse.Companion.fromDomain(wallet: Wallet) =
    WalletBalanceResponse(balance = wallet.balance.amount, currency = wallet.balance.currency.name)
