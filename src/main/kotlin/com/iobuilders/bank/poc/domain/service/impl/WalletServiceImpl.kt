package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import com.iobuilders.bank.poc.domain.service.WalletService
import mu.KotlinLogging

class WalletServiceImpl(private val walletRepository: WalletRepository) : WalletService {

    private val logger = KotlinLogging.logger {}

    override fun createWallet(user: User): Wallet {
        logger.info { "creating wallet for userId ${user.id}" }
        return walletRepository
            .createWallet(Wallet(user = user, balance = Money(amount = 0F, currency = MoneyCurrency.FIAT)))
            .apply {
                logger.info { "wallet for userId ${user.id} succesfully created with walletId ${this.id}" }
            }
    }

    override fun findUserWallets(userId: Long): List<Wallet> {
        return walletRepository.findWalletsByUserId(userId)
    }
}
