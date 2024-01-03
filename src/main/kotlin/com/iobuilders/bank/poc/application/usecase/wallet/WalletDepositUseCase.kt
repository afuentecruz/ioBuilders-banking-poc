package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.request.wallet.DepositWalletRequest
import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.toResponse
import com.iobuilders.bank.poc.domain.service.WalletService

class WalletDepositUseCase(private val walletService: WalletService) {
    fun deposit(walletId: Long, depositWalletRequest: DepositWalletRequest): List<WalletResponse> =
        walletService.depositWallet(walletId).map { WalletResponse.toResponse(it) }
}
