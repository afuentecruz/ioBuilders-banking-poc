package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UserNotFoundException
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.domain.service.WalletService
import com.iobuilders.bank.poc.utils.userTestData
import com.iobuilders.bank.poc.utils.walletTestData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WalletCreateUseCaseTest {

    private var userService: UserService = mockk()

    private var walletService: WalletService = mockk()

    private val walletCreateUseCase: WalletCreateUseCase = WalletCreateUseCase(userService, walletService)

    @Test
    fun whenCreateWalletForExistingUser_thenReturnCreatedWalletWithZeroBalance() {
        // given
        val userId: Long = 1L
        val user: User = User.userTestData(userId = userId)
        val wallet: Wallet = Wallet.walletTestData(user = user)
        every { userService.findUser(userId) } returns (user)
        every { walletService.createWallet(user) } returns (wallet)
        // when
        val result = walletCreateUseCase.createUserWallet(userId)
        // then
        Assertions.assertEquals(wallet.balance.amount, result.balance)
        Assertions.assertEquals(wallet.balance.currency.name, result.currency)
        Assertions.assertEquals(wallet.id, result.id)
    }

    @Test
    fun whenCreateWalletForNonExistingUser_shouldThrowException() {
        // given
        val userId: Long = 1L
        every { userService.findUser(userId) } throws (UserNotFoundException(userId))
        // when
        val result =
            Assertions.assertThrows(UserNotFoundException::class.java) { walletCreateUseCase.createUserWallet(userId) }
        // then
        Assertions.assertEquals("User with id $userId not found", result.message)
        verify(exactly = 1) { userService.findUser(userId) }
    }

}
