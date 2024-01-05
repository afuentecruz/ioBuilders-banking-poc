package com.iobuilders.bank.poc.application.usecase.movement

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.WalletService

class WithdrawMovementUseCase(
    private val movementService: MovementService,
    private val walletService: WalletService
) {
    fun withdrawMovement(wallet: Wallet, money: Money): Movement =
        walletService.withdraw(wallet, money.amount).let {
            movementService.doMovement(it, money, MovementType.WITHDRAW)
        }
}
