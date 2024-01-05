package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.application.rest.request.wallet.WalletDepositRequest
import com.iobuilders.bank.poc.domain.*
import com.iobuilders.bank.poc.domain.exception.WalletCurrencyNotFoundException
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.WalletService
import com.iobuilders.bank.poc.utils.testData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class WalletDepositUseCaseTest {

    private var walletService: WalletService = mockk()
    private var movementService: MovementService = mockk()

    private val walletDepositUseCase: WalletDepositUseCase = WalletDepositUseCase(walletService, movementService)

    @Test
    fun depositMovementOverNotExistingWalletForCurrency_shouldThrowWalletCurrencyNotFoundException() {
        // given
        val walletId: Long = 1L
        val walletDepositRequest: WalletDepositRequest =
            WalletDepositRequest(amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name)

        every {
            walletService.findWalletCurrency(
                walletId, walletDepositRequest.currency
            )
        } throws WalletCurrencyNotFoundException(walletId, walletDepositRequest.currency)

        // when
        val result = Assertions.assertThrows(WalletCurrencyNotFoundException::class.java) {
            walletDepositUseCase.deposit(walletId, walletDepositRequest)
        }

        // then
        Assertions.assertEquals(
            "Wallet with id $walletId and currency ${walletDepositRequest.currency} not found", result.message
        )
        verify(exactly = 1) { walletService.findWalletCurrency(walletId, walletDepositRequest.currency) }
    }

    @Test
    fun depositMovementOverExistingWalletForCurrency_shouldCallMovementServiceAndWalletService_returnWalletResponse() {
        // given
        val wallet: Wallet = Wallet.testData()
        val walletDepositRequest: WalletDepositRequest =
            WalletDepositRequest(amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name)
        every { walletService.findWalletCurrency(wallet.id!!, walletDepositRequest.currency) } returns wallet
        every {
            movementService.doMovement(
                wallet, Money(walletDepositRequest.amount, wallet.balance.currency), MovementType.DEPOSIT
            )
        } returns Movement.testData()
        every {
            walletService.deposit(wallet, walletDepositRequest.amount)
        } returns wallet.copy(
            balance = Money(
                currency = MoneyCurrency.valueOf(walletDepositRequest.currency),
                amount = wallet.balance.amount.plus(walletDepositRequest.amount)
            )
        )

        // when
        val result = walletDepositUseCase.deposit(wallet.id!!, walletDepositRequest)


        // then
        Assertions.assertEquals(wallet.balance.amount.plus(walletDepositRequest.amount), result.balance)
        Assertions.assertEquals(wallet.balance.currency.name, result.currency)
        Assertions.assertEquals(wallet.id!!, result.id)
        verify(exactly = 1) { walletService.findWalletCurrency(wallet.id!!, walletDepositRequest.currency) }
        verify(exactly = 1) {
            movementService.doMovement(
                wallet, Money(walletDepositRequest.amount, wallet.balance.currency), MovementType.DEPOSIT
            )
        }
    }
}

