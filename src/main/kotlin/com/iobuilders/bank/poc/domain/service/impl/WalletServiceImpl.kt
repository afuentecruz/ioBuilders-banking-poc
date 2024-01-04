package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.*
import com.iobuilders.bank.poc.domain.exception.WalletNotFoundException
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import com.iobuilders.bank.poc.domain.service.WalletService
import mu.KotlinLogging

class WalletServiceImpl(private val walletRepository: WalletRepository) : WalletService {

    private val logger = KotlinLogging.logger {}

    override fun createWallet(user: User): Wallet {
        logger.info { "creating wallet for userId ${user.id}" }
        return walletRepository
            .saveWallet(Wallet(user = user, balance = Money(amount = 0F, currency = MoneyCurrency.EUR)))
            .also {
                logger.info { "wallet for userId ${user.id} successfully created with id ${it.id}" }
            }
    }

    override fun findUserWallets(userId: Long): List<Wallet> {
        return walletRepository.findWalletsByUserId(userId)
    }

    override fun findWalletCurrency(walletId: Long, currency: String): Wallet =
        walletRepository.findWalletCurrency(walletId, currency) ?: throw WalletNotFoundException(walletId, currency)


    override fun deposit(wallet: Wallet, amount: Float): Wallet =
        wallet.addAmount(amount).apply { walletRepository.saveWallet(this) }

}
