package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UserWalletNotFoundException
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.WalletService
import com.iobuilders.bank.poc.utils.walletTestData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WalletDetailsUseCaseTest {

    private var walletService: WalletService = mockk()
    private var movementService: MovementService = mockk()

    private val walletDetailsUseCase = WalletDetailsUseCase(walletService, movementService)

    @Test
    fun whenFindWalletsForUserWithNoWallet_shouldThrowException() {
        // given
        val userId: Long = 1L
        every { walletService.findUserWallets(userId) } throws UserWalletNotFoundException(userId)
        // when
        val result = Assertions.assertThrows(UserWalletNotFoundException::class.java) {
            walletDetailsUseCase.getUserWallets(userId)
        }
        // then
        Assertions.assertEquals("Wallet for userId $userId not found", result.message)
        verify(exactly = 1) { walletService.findUserWallets(userId) }
    }

    @Test
    fun whenFindWalletsForCreatedUserWallets_shouldReturnTheWallets() {
        // given
        val userId: Long = 1L
        val wallet = Wallet.walletTestData()
        every { walletService.findUserWallets(userId) } returns (listOf(wallet))
        // when
        val result = walletDetailsUseCase.getUserWallets(userId)
        // then
        Assertions.assertTrue(result.isNotEmpty())
        Assertions.assertEquals(wallet.id, result.first().id)
        Assertions.assertEquals(wallet.balance.amount, result.first().balance)
        Assertions.assertEquals(wallet.balance.currency.name, result.first().currency)
    }
}
