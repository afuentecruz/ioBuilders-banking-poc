package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.repository.MovementRepository
import com.iobuilders.bank.poc.domain.service.MovementService

class MovementServiceImpl(
    private val movementRepository: MovementRepository
) : MovementService {

    override fun doMovement(wallet: Wallet, money: Money, type: MovementType): Unit =
        Movement(type = type, money = money, wallet = wallet)
            .let { movementRepository.save(it) }

    override fun findWalletMovements(walletId: Long): List<Movement> =
        movementRepository.findAllWalletMovements(walletId)

}
