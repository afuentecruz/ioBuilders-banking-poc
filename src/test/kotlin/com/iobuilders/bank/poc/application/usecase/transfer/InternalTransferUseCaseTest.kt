package com.iobuilders.bank.poc.application.usecase.transfer

import com.iobuilders.bank.poc.application.rest.request.transfer.InternalTransferRequest
import com.iobuilders.bank.poc.domain.*
import com.iobuilders.bank.poc.domain.exception.ForbiddenWalletUsageException
import com.iobuilders.bank.poc.domain.exception.InsufficientFundsException
import com.iobuilders.bank.poc.domain.exception.SameWalletsException
import com.iobuilders.bank.poc.domain.exception.WalletCurrencyNotFoundException
import com.iobuilders.bank.poc.domain.service.AmlValidationService
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.TransferService
import com.iobuilders.bank.poc.domain.service.WalletService
import com.iobuilders.bank.poc.utils.testData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class InternalTransferUseCaseTest {

    private var walletService: WalletService = mockk()
    private var movementService: MovementService = mockk()
    private var transferService: TransferService = mockk()
    private var amlValidationService: AmlValidationService = mockk()

    private val internalTransferUseCase: InternalTransferUseCase = InternalTransferUseCase(
        walletService, movementService, transferService, amlValidationService
    )

    @Test
    fun internalTransferOverNonExistingOriginWalletCurrency_shouldThrowWalletCurrencyNotFoundException() {
        // given
        val originWalletId = 1L
        val currency: String = MoneyCurrency.EUR.name
        val user: User = User.testData()
        val internalTransferRequest = InternalTransferRequest(
            amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name, from = 1L, to = 2L
        )
        every { walletService.findWalletCurrency(originWalletId, currency) } throws WalletCurrencyNotFoundException(
            originWalletId, currency
        )

        // when
        val result = Assertions.assertThrows(WalletCurrencyNotFoundException::class.java) {
            internalTransferUseCase.internalTransfer(user.username, internalTransferRequest)
        }

        // then
        Assertions.assertEquals(("Wallet with id $originWalletId and currency $currency not found"), result.message)
        verify { walletService.findWalletCurrency(originWalletId, currency) }
    }


    @Test
    fun internalTransferOverNonExistingDestinationWalletCurrency_shouldThrowWalletCurrencyNotFoundException() {
        // given
        val originWallet = Wallet.testData()
        val destinationWalletId = 2L

        val currency: String = MoneyCurrency.EUR.name
        val user: User = User.testData()
        val internalTransferRequest = InternalTransferRequest(
            amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name, from = 1L, to = 2L
        )
        every { walletService.findWalletCurrency(originWallet.id!!, currency) } returns originWallet
        every {
            walletService.findWalletCurrency(
                destinationWalletId, currency
            )
        } throws WalletCurrencyNotFoundException(destinationWalletId, currency)

        // when
        val result = Assertions.assertThrows(WalletCurrencyNotFoundException::class.java) {
            internalTransferUseCase.internalTransfer(user.username, internalTransferRequest)
        }

        // then
        Assertions.assertEquals(
            ("Wallet with id $destinationWalletId and currency $currency not found"), result.message
        )
        verify { walletService.findWalletCurrency(originWallet.id!!, currency) }
        verify { walletService.findWalletCurrency(destinationWalletId, currency) }
    }


    @Test
    fun internalTransferBetweenExistingWallets_butUserHasNoOwnership_shouldThrowForbiddenWalletUsageException() {
        // given
        val user = User.testData()
        val origin = Wallet.testData(id = 1L)
        val destination = Wallet.testData(id = 2L)

        val currency: String = MoneyCurrency.EUR.name
        val internalTransferRequest = InternalTransferRequest(
            amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name, from = 1L, to = 2L
        )
        every { walletService.findWalletCurrency(origin.id!!, currency) } returns origin
        every {
            walletService.findWalletCurrency(
                destination.id!!, currency
            )
        } returns destination
        every {
            amlValidationService.checkWalletOwnership(user.username, origin.id!!)
        } throws ForbiddenWalletUsageException()

        // when
        val result = Assertions.assertThrows(ForbiddenWalletUsageException::class.java) {
            internalTransferUseCase.internalTransfer(user.username, internalTransferRequest)
        }

        // then
        Assertions.assertEquals(
            ("You are not allowed to use this wallet"), result.message
        )
        verify { walletService.findWalletCurrency(origin.id!!, currency) }
        verify { walletService.findWalletCurrency(destination.id!!, currency) }
        verify { amlValidationService.checkWalletOwnership(user.username, origin.id!!) }
    }

    @Test
    fun internalTransferBetweenExistingWallets_butOriginWalletDoesNotHaveEnoughFunds_shouldThrowInsufficientFundsException() {
        // given
        val user = User.testData()
        val origin = Wallet.testData(id = 1L, balance = Money(BigDecimal.ONE, currency = MoneyCurrency.EUR))
        val destination = Wallet.testData(id = 2L)

        val currency: String = MoneyCurrency.EUR.name
        val internalTransferRequest = InternalTransferRequest(
            amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name, from = 1L, to = 2L
        )
        every { walletService.findWalletCurrency(origin.id!!, currency) } returns origin
        every {
            walletService.findWalletCurrency(
                destination.id!!, currency
            )
        } returns destination
        every {
            amlValidationService.checkWalletOwnership(user.username, origin.id!!)
        } returns Unit
        every {
            amlValidationService.checkWalletBalance(origin, internalTransferRequest.amount)
        } throws InsufficientFundsException()

        // when
        val result = Assertions.assertThrows(InsufficientFundsException::class.java) {
            internalTransferUseCase.internalTransfer(user.username, internalTransferRequest)
        }

        // then
        Assertions.assertEquals(
            ("Wallet hasn't had enough funds to perform this operation"), result.message
        )
        verify { walletService.findWalletCurrency(origin.id!!, currency) }
        verify { walletService.findWalletCurrency(destination.id!!, currency) }
        verify { amlValidationService.checkWalletOwnership(user.username, origin.id!!) }
        verify { amlValidationService.checkWalletBalance(origin, internalTransferRequest.amount) }
    }

    @Test
    fun internalTransferBetweenExistingWallets_butWalletsAreTheSame_shouldSameWalletsException() {
        // given
        val user = User.testData()
        val origin = Wallet.testData(id = 1L, balance = Money(BigDecimal.ONE, currency = MoneyCurrency.EUR))
        val destination = Wallet.testData(id = 2L)

        val currency: String = MoneyCurrency.EUR.name
        val internalTransferRequest = InternalTransferRequest(
            amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name, from = 1L, to = 2L
        )
        every { walletService.findWalletCurrency(origin.id!!, currency) } returns origin
        every {
            walletService.findWalletCurrency(
                destination.id!!, currency
            )
        } returns destination
        every {
            amlValidationService.checkWalletOwnership(user.username, origin.id!!)
        } returns Unit
        every {
            amlValidationService.checkWalletBalance(origin, internalTransferRequest.amount)
        } returns Unit
        every {
            amlValidationService.checkWalletsAreNotTheSame(origin, destination)
        } throws SameWalletsException()

        // when
        val result = Assertions.assertThrows(SameWalletsException::class.java) {
            internalTransferUseCase.internalTransfer(user.username, internalTransferRequest)
        }

        // then
        Assertions.assertEquals(
            ("Origin and destination Wallet cannot be the same"), result.message
        )
        verify { walletService.findWalletCurrency(origin.id!!, currency) }
        verify { walletService.findWalletCurrency(destination.id!!, currency) }
        verify { amlValidationService.checkWalletOwnership(user.username, origin.id!!) }
        verify { amlValidationService.checkWalletBalance(origin, internalTransferRequest.amount) }
        verify { amlValidationService.checkWalletsAreNotTheSame(origin, destination) }
    }


    @Test
    fun internalTransferBetween_shouldGenerateWithdrawMovementInOriginAndDepositInDestination_thenCallWalletDepositAndWithdraw_thenCallDoTransfer() {
        // given
        val internalTransferRequest = InternalTransferRequest(
            amount = BigDecimal.TEN, currency = MoneyCurrency.EUR.name, from = 1L, to = 2L
        )
        val user = User.testData()
        val origin = Wallet.testData(id = 1L, balance = Money(BigDecimal.TEN, currency = MoneyCurrency.EUR))
        val originWithdraw = origin.copy(
            balance = Money(
                origin.balance.amount.minus(internalTransferRequest.amount), currency = origin.balance.currency
            )
        )
        val destination = Wallet.testData(id = 2L)
        val destinationDeposit = destination.copy(
            balance = Money(
                destination.balance.amount.plus(internalTransferRequest.amount), currency = origin.balance.currency
            )
        )

        val currency: String = MoneyCurrency.EUR.name

        val transferMoney = Money(
            amount = internalTransferRequest.amount, currency = MoneyCurrency.valueOf(internalTransferRequest.currency)
        )

        every { walletService.findWalletCurrency(origin.id!!, currency) } returns origin
        every {
            walletService.findWalletCurrency(
                destination.id!!, currency
            )
        } returns destination
        every {
            amlValidationService.checkWalletOwnership(user.username, origin.id!!)
        } returns Unit
        every {
            amlValidationService.checkWalletBalance(origin, internalTransferRequest.amount)
        } returns Unit
        every {
            amlValidationService.checkWalletsAreNotTheSame(origin, destination)
        } returns Unit
        every {
            movementService.doMovement(origin, transferMoney, MovementType.WITHDRAW)
        } returns Movement.testData()
        every {
            movementService.doMovement(destination, transferMoney, MovementType.DEPOSIT)
        } returns Movement.testData()
        every {
            walletService.withdraw(origin, transferMoney.amount)
        } returns originWithdraw

        every {
            walletService.deposit(destination, transferMoney.amount)
        } returns destinationDeposit
        every {
            transferService.doTransfer(originWithdraw, destinationDeposit, transferMoney)
        } returns Transfer.testData()
        // when
        val result = internalTransferUseCase.internalTransfer(user.username, internalTransferRequest)

        // then
        verify { walletService.findWalletCurrency(origin.id!!, currency) }
        verify { walletService.findWalletCurrency(destination.id!!, currency) }
        verify { amlValidationService.checkWalletOwnership(user.username, origin.id!!) }
        verify { amlValidationService.checkWalletBalance(origin, internalTransferRequest.amount) }
        verify { amlValidationService.checkWalletsAreNotTheSame(origin, destination) }
        verify { movementService.doMovement(origin, transferMoney, MovementType.WITHDRAW) }
        verify { walletService.withdraw(origin, transferMoney.amount) }
        verify { movementService.doMovement(destination, transferMoney, MovementType.DEPOSIT) }
        verify { walletService.deposit(destination, transferMoney.amount) }
        verify {
            transferService.doTransfer(originWithdraw, destinationDeposit, transferMoney)
        }
    }


}
