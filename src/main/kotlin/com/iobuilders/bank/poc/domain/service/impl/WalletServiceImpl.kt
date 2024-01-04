package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.*
import com.iobuilders.bank.poc.domain.exception.UserWalletNotFoundException
import com.iobuilders.bank.poc.domain.exception.WalletCurrencyNotFoundException
import com.iobuilders.bank.poc.domain.exception.WalletNotFoundException
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import com.iobuilders.bank.poc.domain.service.WalletService
import mu.KotlinLogging
import java.math.BigDecimal

class WalletServiceImpl(
    private val walletRepository: WalletRepository
) : WalletService {

    private val logger = KotlinLogging.logger {}

    override fun createWallet(user: User): Wallet {
        logger.info { "creating wallet for userId ${user.id}" }
        return walletRepository
            .saveWallet(Wallet(user = user, balance = Money(amount = BigDecimal.ZERO, currency = MoneyCurrency.EUR)))
            .also {
                logger.info { "wallet for userId ${user.id} successfully created with id ${it.id}" }
            }
    }

    override fun findWallet(walletId: Long): Wallet =
        walletRepository.findWallet(walletId) ?: throw WalletNotFoundException(walletId)


    override fun findUserWallets(userId: Long): List<Wallet> {
        return walletRepository.findWalletsByUserId(userId).ifEmpty { throw UserWalletNotFoundException(userId) }
    }

    override fun findWalletCurrency(walletId: Long, currency: String): Wallet =
        walletRepository.findWalletCurrency(walletId, currency) ?: throw WalletCurrencyNotFoundException(
            walletId,
            currency
        )


    override fun deposit(wallet: Wallet, amount: BigDecimal): Wallet =
        wallet.addAmount(amount).apply { walletRepository.saveWallet(this) }

    override fun withdraw(wallet: Wallet, amount: BigDecimal): Wallet =
        wallet.minusAmount(amount).apply { walletRepository.saveWallet(this) }

}
