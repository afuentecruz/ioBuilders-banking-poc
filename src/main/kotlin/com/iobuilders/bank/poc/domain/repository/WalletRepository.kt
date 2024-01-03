package com.iobuilders.bank.poc.domain.repository

import com.iobuilders.bank.poc.domain.Wallet

interface WalletRepository {
    fun createWallet(wallet: Wallet): Wallet
    fun findWalletsByUserId(userId: Long): List<Wallet>
}
