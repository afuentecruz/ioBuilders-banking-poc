package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.generateDepositMovement
import com.iobuilders.bank.poc.domain.repository.MovementRepository
import com.iobuilders.bank.poc.domain.service.MovementService

class MovementServiceImpl(private val movementRepository: MovementRepository) : MovementService {
    override fun generateDeposit(wallet: Wallet, amount: Float): Movement =
        Movement.generateDepositMovement(
            money = Money(amount = amount, currency = wallet.balance.currency),
            destinationWallet = wallet
        ).apply { movementRepository.save(this) }
}
