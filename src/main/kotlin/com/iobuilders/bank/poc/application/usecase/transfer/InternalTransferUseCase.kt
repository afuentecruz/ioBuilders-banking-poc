package com.iobuilders.bank.poc.application.usecase.transfer

import com.iobuilders.bank.poc.application.rest.request.transfer.TransferRequest
import com.iobuilders.bank.poc.application.usecase.movement.DepositMovementUseCase
import com.iobuilders.bank.poc.application.usecase.movement.WithdrawMovementUseCase
import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.TransferStatus
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.service.AmlValidationService
import com.iobuilders.bank.poc.domain.service.TransferService
import com.iobuilders.bank.poc.domain.service.WalletService

class InternalTransferUseCase(
    private val withdrawMovementUseCase: WithdrawMovementUseCase,
    private val depositMovementUseCase: DepositMovementUseCase,
    private val walletService: WalletService,
    private val transferService: TransferService,
    private val amlValidationService: AmlValidationService
) {

    fun internalTransfer(username: String, transferRequest: TransferRequest) {
        val originWallet: Wallet =
            walletService.findWalletCurrency(transferRequest.from, transferRequest.currency)
        val destinationWallet: Wallet =
            walletService.findWalletCurrency(transferRequest.to, transferRequest.currency)

        validateInternalTransfer(username, originWallet, destinationWallet, transferRequest)

        buildTransferMoney(transferRequest).let { transferMoney ->
            transferService.doTransfer(originWallet, destinationWallet, transferMoney).let { transfer ->
                if (TransferStatus.COMPLETED == transfer.status) {
                    withdrawMovementUseCase.withdrawMovement(originWallet, transferMoney)
                    depositMovementUseCase.depositMovement(destinationWallet, transferMoney)
                }
            }
        }
    }

    private fun buildTransferMoney(transferRequest: TransferRequest) = Money(
        amount = transferRequest.amount, currency = MoneyCurrency.valueOf(transferRequest.currency)
    )

    private fun validateInternalTransfer(
        username: String,
        originWallet: Wallet,
        destinationWallet: Wallet,
        transferRequest: TransferRequest
    ) {
        amlValidationService.checkWalletOwnership(username, originWallet.id!!)
        amlValidationService.checkWalletBalance(originWallet, transferRequest.amount)
        amlValidationService.checkWalletsAreNotTheSame(originWallet, destinationWallet)
    }
}
