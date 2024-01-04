package com.iobuilders.bank.poc.domain.repository

import com.iobuilders.bank.poc.domain.Movement

interface MovementRepository {
    fun save(movement: Movement): Movement
    fun findAllWalletMovements(walletId: Long): List<Movement>
}
