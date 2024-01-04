package com.iobuilders.bank.poc.application.usecase.transfer

import com.iobuilders.bank.poc.application.rest.request.transfer.InternalTransferRequest
import com.iobuilders.bank.poc.domain.*
import com.iobuilders.bank.poc.domain.exception.ForbiddenWalletUsageException
import com.iobuilders.bank.poc.domain.exception.InsufficientFundsException
import com.iobuilders.bank.poc.domain.exception.SameWalletsException
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.TransferService
import com.iobuilders.bank.poc.domain.service.WalletService
import java.math.BigDecimal

class InternalTransferUseCase(
    private val walletService: WalletService,
    private val movementService: MovementService,
    private val transferService: TransferService,
) {

    fun internalTransfer(internalTransferRequest: InternalTransferRequest) {
        val originWallet: Wallet =
            walletService.findWalletCurrency(internalTransferRequest.from, internalTransferRequest.currency)
        val destinationWallet: Wallet =
            walletService.findWalletCurrency(internalTransferRequest.to, internalTransferRequest.currency)

        validate(originWallet, destinationWallet, internalTransferRequest)

        val transferMoney = Money(
            amount = internalTransferRequest.amount, currency = MoneyCurrency.valueOf(internalTransferRequest.currency)
        )

        movementService.doMovement(originWallet, transferMoney, MovementType.WITHDRAW).also {
            walletService.withdraw(originWallet, transferMoney.amount)
        }
        movementService.doMovement(destinationWallet, transferMoney, MovementType.DEPOSIT).also {
            walletService.deposit(destinationWallet, transferMoney.amount)
        }

        transferService.doTransfer(originWallet, destinationWallet, transferMoney)
    }

    private fun validate(
        originWallet: Wallet,
        destinationWallet: Wallet,
        internalTransferRequest: InternalTransferRequest
    ) {
        checkWalletOwnership(internalTransferRequest.orderedBy, originWallet)
        checkWalletBalance(originWallet, internalTransferRequest.amount)
        checkWalletsAreNotTheSame(originWallet, destinationWallet)
    }

    private fun checkWalletOwnership(orderedBy: Long, originWallet: Wallet) =
        walletService.findUserWallets(orderedBy).let { userWallets ->
            if (originWallet !in userWallets) throw ForbiddenWalletUsageException()
        }

    private fun checkWalletBalance(wallet: Wallet, amount: BigDecimal) =
        wallet.hasEnoughBalance(amount).let { hasEnough -> if (!hasEnough) throw InsufficientFundsException() }

    private fun checkWalletsAreNotTheSame(origin: Wallet, destination: Wallet) {
        if (origin.id!! == destination.id!!) throw SameWalletsException()
    }
}
