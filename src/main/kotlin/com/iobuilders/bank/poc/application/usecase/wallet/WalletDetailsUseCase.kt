package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletResponse
import com.iobuilders.bank.poc.application.rest.response.wallet.toResponse
import com.iobuilders.bank.poc.domain.service.WalletService

class WalletDetailsUseCase(private val walletService: WalletService) {
    fun getUserWallets(userId: Long): List<WalletResponse> =
        walletService.findUserWallets(userId).map { WalletResponse.toResponse(it) }
}
