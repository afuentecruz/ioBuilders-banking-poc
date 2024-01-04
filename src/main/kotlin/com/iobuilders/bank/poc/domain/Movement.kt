package com.iobuilders.bank.poc.domain

data class Movement(
    val movementId: Long? = null,
    val type: MovementType,
    val money: Money,
    val destinationWallet: Wallet? = null,
    val originWallet: Wallet? = null
) {
    companion object
}

fun Movement.Companion.generateDepositMovement(money: Money, destinationWallet: Wallet): Movement =
    Movement(type = MovementType.DEPOSIT, money = money, destinationWallet = destinationWallet, originWallet = null)
