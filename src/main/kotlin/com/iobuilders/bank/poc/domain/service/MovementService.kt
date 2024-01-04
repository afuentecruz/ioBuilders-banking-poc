package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.Wallet

interface MovementService {
    fun generateDeposit(wallet: Wallet, amount: Float): Movement
}
