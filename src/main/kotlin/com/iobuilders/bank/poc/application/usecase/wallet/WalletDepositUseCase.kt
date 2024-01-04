package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.request.wallet.WalletDepositRequest
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.fromDomain
import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.WalletService

class WalletDepositUseCase(
    private val walletService: WalletService,
    private val movementService: MovementService
) {
    fun deposit(walletId: Long, walletDepositRequest: WalletDepositRequest): WalletResponse {
        walletService.findWalletCurrency(walletId, walletDepositRequest.currency).apply {
            movementService.doMovement(
                this,
                Money(walletDepositRequest.amount, this.balance.currency),
                MovementType.DEPOSIT
            ).also {
                walletService.deposit(this, walletDepositRequest.amount).let {
                    return WalletResponse.fromDomain(it)
                }
            }
        }
    }
}
