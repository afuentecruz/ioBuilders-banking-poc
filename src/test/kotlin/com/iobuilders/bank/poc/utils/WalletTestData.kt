package com.iobuilders.bank.poc.utils

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet

fun Wallet.Companion.walletTestData(
    id: Long? = 1L,
    user: User = User.userTestData(),
    balance: Money = Money(amount = 1F, currency = MoneyCurrency.FIAT)
) = Wallet(id = id, user = user, balance = balance)

