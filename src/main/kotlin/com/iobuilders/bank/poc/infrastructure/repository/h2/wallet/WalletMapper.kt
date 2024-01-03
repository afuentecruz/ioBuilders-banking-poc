package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.toDomain
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.toEntity

fun WalletEntity.Companion.toEntity(wallet: Wallet): WalletEntity =
    WalletEntity(
        id = wallet.id,
        amount = wallet.balance.amount,
        currency = wallet.balance.currency.name,
        user = UserEntity.toEntity(wallet.user)
    )

fun WalletEntity.Companion.toDomain(walletEntity: WalletEntity): Wallet = Wallet(
    id = walletEntity.id,
    user = UserEntity.toDomain(walletEntity.user),
    balance = Money(amount = walletEntity.amount, currency = MoneyCurrency.valueOf(walletEntity.currency))
)
