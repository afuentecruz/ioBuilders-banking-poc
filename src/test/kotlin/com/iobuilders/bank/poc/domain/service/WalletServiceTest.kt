package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.exception.UserWalletNotFoundException
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import com.iobuilders.bank.poc.domain.service.impl.WalletServiceImpl
import com.iobuilders.bank.poc.utils.userTestData
import com.iobuilders.bank.poc.utils.walletTestData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WalletServiceTest {

    private var walletRepository: WalletRepository = mockk()

    private val walletService: WalletService = WalletServiceImpl(walletRepository)

    @Test
    fun whenCreateWalletOfExistingUser_shouldReturnTheCreatedWallet() {
        // given
        val user: User = User.userTestData()
        val wallet: Wallet =
            Wallet.walletTestData(id = null, balance = Money(amount = 0F, currency = MoneyCurrency.EUR))
        val createdWallet: Wallet = Wallet.walletTestData(balance = Money(amount = 0F, currency = MoneyCurrency.EUR))
        every { walletRepository.saveWallet(wallet) } returns (createdWallet)
        // when
        val result = walletService.createWallet(user)
        // then
        Assertions.assertEquals(wallet.balance.amount, result.balance.amount)
        Assertions.assertEquals(wallet.balance.currency, result.balance.currency)
        Assertions.assertEquals(wallet.user.id, result.user.id)
        Assertions.assertEquals(wallet.user.username, result.user.username)
        Assertions.assertEquals(wallet.user.password, result.user.password)
        Assertions.assertEquals(1, result.id)
    }

    @Test
    fun whenFindWalletsByUserIdOfExistingUser_shouldReturnTheWallets() {
        // given
        val user: User = User.userTestData()
        val wallet: Wallet = Wallet.walletTestData(user = user)
        every { walletRepository.findWalletsByUserId(user.id!!) } returns (listOf(wallet))
        // when
        val result = walletService.findUserWallets(user.id!!)
        // then
        Assertions.assertTrue(result.isNotEmpty())
        Assertions.assertEquals(wallet.id!!, result.first().id!!)
        Assertions.assertEquals(wallet.balance.amount, result.first().balance.amount)
        Assertions.assertEquals(wallet.balance.currency, result.first().balance.currency)
        Assertions.assertEquals(wallet.user.id, result.first().user.id)
        Assertions.assertEquals(wallet.user.username, result.first().user.username)
        Assertions.assertEquals(wallet.user.password, result.first().user.password)
    }

    @Test
    fun whenFindWalletsByUserIdOfExistingUserButWithNoWallets_shouldThrowException() {
        // given
        val user: User = User.userTestData()
        every { walletRepository.findWalletsByUserId(user.id!!) } throws (UserWalletNotFoundException(user.id!!))
        // when
        val result =
            Assertions.assertThrows(UserWalletNotFoundException::class.java) { walletService.findUserWallets(user.id!!) }
        // then
        Assertions.assertEquals("Wallet for userId ${user.id} not found", result.message)
    }
}
