package com.iobuilders.bank.poc.utils

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet
import java.time.LocalDateTime

fun Movement.Companion.testData(): Movement = Movement(
    movementId = 1L,
    type = MovementType.DEPOSIT,
    money = Money.testData(),
    wallet = Wallet.testData(),
    creationDate = LocalDateTime.now()
)
