package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.ForbiddenWalletUsageException
import com.iobuilders.bank.poc.domain.exception.InsufficientFundsException
import com.iobuilders.bank.poc.domain.exception.SameWalletsException
import com.iobuilders.bank.poc.domain.hasEnoughBalance
import com.iobuilders.bank.poc.domain.service.AmlValidationService
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.domain.service.WalletService
import java.math.BigDecimal

class AmlValidationServiceImpl(
    private val walletService: WalletService,
    private val userService: UserService
) : AmlValidationService {

    override fun checkWalletOwnership(username: String, wallet: Wallet) =
        userService.findUsername(username).let { user ->
            walletService.findUserWallets(user.id!!).let { userWallets ->
                if (wallet !in userWallets) throw ForbiddenWalletUsageException()
            }
        }

    override fun checkWalletOwnershipByWalletId(username: String, walletId: Long) =
        userService.findUsername(username).let { user ->
            walletService.findUserWallets(user.id!!).let { userWallets ->
                if (walletId !in userWallets.map { it.id }) throw ForbiddenWalletUsageException()
            }
        }

    override fun checkWalletBalance(wallet: Wallet, amount: BigDecimal) =
        wallet.hasEnoughBalance(amount).let { hasEnough -> if (!hasEnough) throw InsufficientFundsException() }

    override fun checkWalletsAreNotTheSame(origin: Wallet, destination: Wallet) {
        if (origin.id!! == destination.id!!) throw SameWalletsException()
    }
}
