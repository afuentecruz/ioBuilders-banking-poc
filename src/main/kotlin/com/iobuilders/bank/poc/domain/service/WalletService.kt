package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet

interface WalletService {
    fun createWallet(user: User): Wallet
    fun findUserWallets(userId: Long): List<Wallet>
    fun findWalletCurrency(walletId: Long, currency: String): Wallet
    fun deposit(wallet: Wallet, amount: Float): Wallet
}
