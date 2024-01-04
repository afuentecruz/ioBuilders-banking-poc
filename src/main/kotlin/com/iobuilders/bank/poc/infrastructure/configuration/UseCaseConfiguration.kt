package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.application.usecase.transfer.InternalTransferUseCase
import com.iobuilders.bank.poc.application.usecase.user.UserDetailsUseCase
import com.iobuilders.bank.poc.application.usecase.user.UserRegistryUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletCreateUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDepositUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDetailsUseCase
import com.iobuilders.bank.poc.domain.service.MovementService
import com.iobuilders.bank.poc.domain.service.TransferService
import com.iobuilders.bank.poc.domain.service.WalletService
import com.iobuilders.bank.poc.domain.service.impl.MovementServiceImpl
import com.iobuilders.bank.poc.domain.service.impl.UserServiceImpl
import com.iobuilders.bank.poc.domain.service.impl.WalletServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun createUserUseCase(
        userService: UserServiceImpl
    ): UserRegistryUseCase = UserRegistryUseCase(userService)

    @Bean
    fun findUserUseCase(
        userService: UserServiceImpl
    ): UserDetailsUseCase = UserDetailsUseCase(userService)

    @Bean
    fun createWalletUseCase(
        userService: UserServiceImpl, walletService: WalletServiceImpl
    ): WalletCreateUseCase = WalletCreateUseCase(userService, walletService)

    @Bean
    fun walletDetailsUseCase(
        walletService: WalletServiceImpl,
        movementService: MovementService
    ): WalletDetailsUseCase = WalletDetailsUseCase(walletService, movementService)

    @Bean
    fun walletDepositUseCase(
        walletService: WalletServiceImpl, movementService: MovementServiceImpl
    ): WalletDepositUseCase = WalletDepositUseCase(walletService, movementService)

    @Bean
    fun internalTransferUSeCase(
        walletService: WalletService,
        movementService: MovementService,
        transferService: TransferService,
    ): InternalTransferUseCase = InternalTransferUseCase(walletService, movementService, transferService)
}
