package com.iobuilders.bank.poc.application.usecase.wallet

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UsernameNotFoundException
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.domain.service.WalletService
import com.iobuilders.bank.poc.utils.testData
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
        val user: User = User.testData()
        val wallet: Wallet = Wallet.testData(user = user)
        every { userService.findUsername(user.username) } returns (user)
        every { walletService.createWallet(user) } returns (wallet)
        // when
        val result = walletCreateUseCase.createUserWallet(user.username)
        // then
        Assertions.assertEquals(wallet.balance.amount, result.balance)
        Assertions.assertEquals(wallet.balance.currency.name, result.currency)
        Assertions.assertEquals(wallet.id, result.id)
    }

    @Test
    fun whenCreateWalletForNonExistingUser_shouldThrowException() {
        // given
        val username: String = "usernameTest"
        every { userService.findUsername(username) } throws (UsernameNotFoundException(username))
        // when
        val result = Assertions.assertThrows(UsernameNotFoundException::class.java) {
            walletCreateUseCase.createUserWallet(
                username
            )
        }
        // then
        Assertions.assertEquals("User with username $username not found", result.message)
        verify(exactly = 1) { userService.findUsername(username) }
    }

}
