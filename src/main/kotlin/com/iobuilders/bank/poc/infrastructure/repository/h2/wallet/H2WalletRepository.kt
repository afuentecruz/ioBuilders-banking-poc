package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UserNotFoundException
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrElse

@Component
class H2WalletRepository(private val walletRepository: SpringDataH2WalletRepository) : WalletRepository {
    override fun createWallet(wallet: Wallet): Wallet =
        WalletEntity.toDomain(walletRepository.save(WalletEntity.fromDomain(wallet)))

}
