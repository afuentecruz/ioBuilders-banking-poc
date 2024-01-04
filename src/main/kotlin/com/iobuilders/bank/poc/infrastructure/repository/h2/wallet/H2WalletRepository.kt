package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UserWalletNotFoundException
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import org.springframework.stereotype.Component

@Component
class H2WalletRepository(private val h2WalletRepository: SpringDataH2WalletRepository) : WalletRepository {

    override fun saveWallet(wallet: Wallet): Wallet =
        h2WalletRepository.save(wallet.toEntity()).toDomain()

    override fun findWalletsByUserId(userId: Long): List<Wallet> =
        h2WalletRepository.findAllByUserId(userId).ifEmpty { throw UserWalletNotFoundException(userId) }
            .map { it.toDomain() }

    override fun findWalletCurrency(walletId: Long, currency: String): Wallet? =
        h2WalletRepository.findByIdAndCurrency(walletId, currency)?.toDomain()

}
