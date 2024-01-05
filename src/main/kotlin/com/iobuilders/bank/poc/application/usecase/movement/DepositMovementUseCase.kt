package com.iobuilders.bank.poc.application.usecase.movement

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.WalletService

class DepositMovementUseCase(
    private val walletService: WalletService, private val movementService: MovementService
) {
    fun depositMovement(wallet: Wallet, money: Money) = walletService.deposit(wallet, money.amount).let {
        movementService.doMovement(wallet, money, MovementType.DEPOSIT)
    }
}
