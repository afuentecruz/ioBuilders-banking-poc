package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet

interface MovementService {
    fun doMovement(wallet: Wallet, money: Money, type: MovementType)
    fun findWalletMovements(walletId: Long): List<Movement>
}
