package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.Wallet

interface WalletService {
    fun createWallet(userId: Long): Wallet
}
