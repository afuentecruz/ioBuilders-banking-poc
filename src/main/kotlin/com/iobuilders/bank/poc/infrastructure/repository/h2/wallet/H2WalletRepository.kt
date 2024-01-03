package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UserWalletNotFoundException
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import org.springframework.stereotype.Component

@Component
class H2WalletRepository(private val walletRepository: SpringDataH2WalletRepository) : WalletRepository {
    override fun saveWallet(wallet: Wallet): Wallet =
        walletRepository.save(WalletEntity.toEntity(wallet)).let { return WalletEntity.toDomain(it) }

    override fun findWalletsByUserId(userId: Long): List<Wallet> =
        walletRepository.findAllByUserId(userId).ifEmpty { throw UserWalletNotFoundException(userId) }.map { WalletEntity.toDomain(it) }

}
