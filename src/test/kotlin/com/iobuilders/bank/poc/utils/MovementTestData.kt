package com.iobuilders.bank.poc.utils

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet
import java.time.LocalDateTime

fun Movement.Companion.testData(
    movementId: Long? = 1L,
    type: MovementType,
    money: Money = Money.testData(),
    wallet: Wallet = Wallet.testData(),
): Movement = Movement(
    movementId = movementId,
    type = type,
    money = money,
    wallet = wallet,
    creationDate = LocalDateTime.now()
)
