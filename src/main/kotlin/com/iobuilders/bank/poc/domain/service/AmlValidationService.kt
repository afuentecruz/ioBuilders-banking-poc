package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.Wallet
import java.math.BigDecimal

interface AmlValidationService {
    fun checkWalletOwnership(username: String, walletId: Long)
    fun checkWalletBalance(wallet: Wallet, amount: BigDecimal)
    fun checkWalletsAreNotTheSame(origin: Wallet, destination: Wallet)
}
