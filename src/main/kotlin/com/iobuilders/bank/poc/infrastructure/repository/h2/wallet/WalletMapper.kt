package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.toDomain
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.toEntity

fun Wallet.toEntity(): WalletEntity =
    WalletEntity(
        id = this.id,
        amount = this.balance.amount,
        currency = this.balance.currency.name,
        user = this.user.toEntity()
    )

fun WalletEntity.toDomain(): Wallet = Wallet(
    id = this.id,
    user = this.user.toDomain(),
    balance = Money(amount = this.amount, currency = MoneyCurrency.valueOf(this.currency))
)
