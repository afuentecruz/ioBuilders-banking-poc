package com.iobuilders.bank.poc.domain

data class Wallet(val id: Long? = null, val user: User, val balance: Money) {
    companion object
}

fun Wallet.addAmount(addAmount: Float): Wallet {
    this.balance.amount += addAmount
    return this;
}
