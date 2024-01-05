package com.iobuilders.bank.poc.application.usecase.transfer

import com.iobuilders.bank.poc.application.rest.request.transfer.InternalTransferRequest
import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.MovementType
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.service.AmlValidationService
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.TransferService
import com.iobuilders.bank.poc.domain.service.WalletService

class InternalTransferUseCase(
    private val walletService: WalletService,
    private val movementService: MovementService,
    private val transferService: TransferService,
    private val amlValidationService: AmlValidationService
) {

    fun internalTransfer(username: String, internalTransferRequest: InternalTransferRequest) {
        var originWallet: Wallet =
            walletService.findWalletCurrency(internalTransferRequest.from, internalTransferRequest.currency)
        var destinationWallet: Wallet =
            walletService.findWalletCurrency(internalTransferRequest.to, internalTransferRequest.currency)

        validate(username, originWallet, destinationWallet, internalTransferRequest)

        val transferMoney = Money(
            amount = internalTransferRequest.amount, currency = MoneyCurrency.valueOf(internalTransferRequest.currency)
        )

        movementService.doMovement(originWallet, transferMoney, MovementType.WITHDRAW).also {
            originWallet = walletService.withdraw(originWallet, transferMoney.amount)
        }
        movementService.doMovement(destinationWallet, transferMoney, MovementType.DEPOSIT).also {
            destinationWallet = walletService.deposit(destinationWallet, transferMoney.amount)
        }

        transferService.doTransfer(originWallet, destinationWallet, transferMoney)
    }

    private fun validate(
        username: String,
        originWallet: Wallet,
        destinationWallet: Wallet,
        internalTransferRequest: InternalTransferRequest
    ) {
        amlValidationService.checkWalletOwnership(username, originWallet.id!!)
        amlValidationService.checkWalletBalance(originWallet, internalTransferRequest.amount)
        amlValidationService.checkWalletsAreNotTheSame(originWallet, destinationWallet)
    }
}
