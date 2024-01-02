package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "`user`")
class WalletEntity(
    @Column(name = "`amount`") val amount: Float,
    @Column(name = "`currency`") val currency: String,
    @Column(name = "`userId´") val userId: Long
) : BaseEntity() {
    companion object
}

// TODO convertir este userId en una FK al uso que se traiga la entidad usuario
fun WalletEntity.Companion.fromDomain(wallet: Wallet) =
    WalletEntity(amount = wallet.balance.amount, currency = wallet.balance.currency.name, userId = wallet.userId)

fun WalletEntity.Companion.toDomain(walletEntity: WalletEntity) =
    Wallet(
        id = walletEntity.id,
        userId = walletEntity.userId,
        balance = Money(amount = walletEntity.amount, currency = MoneyCurrency.valueOf(walletEntity.currency))
    )
