package com.iobuilders.bank.poc.infrastructure.repository.h2.movement

import com.iobuilders.bank.poc.domain.Movement
import com.iobuilders.bank.poc.domain.repository.MovementRepository
import org.springframework.stereotype.Component

@Component
class H2MovementRepository(private val h2MovementRepository: SpringDataH2MovementRepository) : MovementRepository {
    override fun save(movement: Movement): Movement =
        h2MovementRepository.save(movement.toEntity()).toDomain()

    override fun findAllWalletMovements(walletId: Long): List<Movement> =
        h2MovementRepository.findAllByWalletId(walletId).map { it.toDomain() }
}
