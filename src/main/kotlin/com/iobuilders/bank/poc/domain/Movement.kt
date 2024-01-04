package com.iobuilders.bank.poc.domain

import java.time.LocalDateTime

data class Movement(
    val movementId: Long? = null,
    val type: MovementType,
    val money: Money,
    val wallet: Wallet,
    val creationDate: LocalDateTime = LocalDateTime.now()
) {
    companion object
}
