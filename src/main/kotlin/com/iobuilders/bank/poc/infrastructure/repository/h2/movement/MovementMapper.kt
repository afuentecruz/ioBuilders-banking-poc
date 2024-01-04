package com.iobuilders.bank.poc.infrastructure.repository.h2.movement

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.infrastructure.repository.h2.wallet.toDomain
import com.iobuilders.bank.poc.infrastructure.repository.h2.wallet.toEntity

fun Movement.toEntity(): MovementEntity = MovementEntity(
    id = this.movementId,
    movementType = this.type.name,
    amount = this.money.amount,
    currency = this.money.currency.name,
    wallet = this.wallet.toEntity(),
    createdAt = this.creationDate
)

fun MovementEntity.toDomain(): Movement = Movement(
    movementId = this.id,
    type = MovementType.valueOf(this.movementType),
    money = Money(amount = this.amount, currency = MoneyCurrency.valueOf(this.currency)),
    wallet = this.wallet.toDomain(),
    creationDate = this.createdAt!!
)
