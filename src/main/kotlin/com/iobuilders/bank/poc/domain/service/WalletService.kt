package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import java.math.BigDecimal

interface WalletService {
    fun createWallet(user: User): Wallet
    fun findWallet(walletId: Long): Wallet
    fun findUserWallets(userId: Long): List<Wallet>
    fun findWalletCurrency(walletId: Long, currency: String): Wallet
    fun deposit(wallet: Wallet, amount: BigDecimal): Wallet
    fun withdraw(wallet: Wallet, amount: BigDecimal): Wallet
}
