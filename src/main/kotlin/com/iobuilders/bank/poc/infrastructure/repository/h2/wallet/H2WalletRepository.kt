package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import org.springframework.stereotype.Component

@Component
class H2WalletRepository(private val walletRepository: SpringDataH2WalletRepository) : WalletRepository {
    override fun createWallet(wallet: Wallet): Wallet =
        WalletEntity.toDomain(walletRepository.save(WalletEntity.toEntity(wallet)))

}
