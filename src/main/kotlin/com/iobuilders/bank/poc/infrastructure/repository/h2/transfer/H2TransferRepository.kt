package com.iobuilders.bank.poc.infrastructure.repository.h2.transfer

import com.iobuilders.bank.poc.domain.Transfer
import com.iobuilders.bank.poc.domain.repository.TransferRepository
import org.springframework.stereotype.Component

@Component
class H2TransferRepository(private val h2TransferRepository: SpringDataH2TransferRepository) : TransferRepository {
    override fun save(transfer: Transfer): Transfer =
        h2TransferRepository.save(transfer.toEntity()).toDomain()
}
