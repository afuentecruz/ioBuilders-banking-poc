package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletBalanceResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletMovementResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.fromDomain
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.WalletService

class WalletDetailsUseCase(private val walletService: WalletService, private val movementService: MovementService) {
    fun getUserWallets(userId: Long): List<WalletResponse> =
        walletService.findUserWallets(userId).map { WalletResponse.fromDomain(it) }

    fun getWalletBalance(walletId: Long): WalletBalanceResponse =
        walletService.findWallet(walletId).let { WalletBalanceResponse.fromDomain(it) }

    fun getWalletMovements(walletId: Long): List<WalletMovementResponse> =
        movementService.findMovementsFromWallet(walletId).map { WalletMovementResponse.fromDomain(it) }
}
