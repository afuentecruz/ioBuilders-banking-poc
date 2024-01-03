package com.iobuilders.bank.poc.domain.repository

import com.iobuilders.bank.poc.domain.Wallet

interface WalletRepository {
    fun saveWallet(wallet: Wallet): Wallet
    fun findWalletsByUserId(userId: Long): List<Wallet>
}
