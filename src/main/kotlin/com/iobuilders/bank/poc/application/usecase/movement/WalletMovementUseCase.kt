package com.iobuilders.bank.poc.application.usecase.movement

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletMovementResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.fromDomain
import com.iobuilders.bank.poc.domain.service.AmlValidationService
import com.iobuilders.bank.poc.domain.service.MovementService

class WalletMovementUseCase(
    private val amlValidationService: AmlValidationService,
    private val movementService: MovementService
) {
    fun getWalletMovements(username: String, walletId: Long): List<WalletMovementResponse> =
        amlValidationService.checkWalletOwnership(username, walletId).run {
            movementService.findWalletMovements(walletId).map { WalletMovementResponse.fromDomain(it) }
        }
}
