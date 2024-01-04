package com.iobuilders.bank.poc.application.rest.response.wallet

import com.iobuilders.bank.poc.domain.Movement
import java.math.BigDecimal
import java.time.LocalDateTime

data class WalletMovementResponse(
    val type: String,
    val amount: BigDecimal,
    val currency: String,
    val creationDate: LocalDateTime
) {
    companion object
}

fun WalletMovementResponse.Companion.fromDomain(movement: Movement): WalletMovementResponse =
    WalletMovementResponse(
        type = movement.type.name,
        amount =  movement.money.amount,
        currency = movement.money.currency.name,
        creationDate =  movement.creationDate
    )
