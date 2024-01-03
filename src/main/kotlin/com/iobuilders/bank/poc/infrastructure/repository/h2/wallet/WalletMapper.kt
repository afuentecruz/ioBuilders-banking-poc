package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.Wallet

fun WalletEntity.Companion.toEntity(wallet: Wallet): WalletEntity =
    WalletEntity(amount = wallet.balance.amount, currency = wallet.balance.currency.name, userId = wallet.userId)

fun WalletEntity.Companion.toDomain(walletEntity: WalletEntity): Wallet = Wallet(
    id = walletEntity.id,
    userId = walletEntity.userId,
    balance = Money(amount = walletEntity.amount, currency = MoneyCurrency.valueOf(walletEntity.currency))
)
