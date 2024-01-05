package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.application.usecase.transfer.InternalTransferUseCase
import com.iobuilders.bank.poc.application.usecase.user.UserDetailsUseCase
import com.iobuilders.bank.poc.application.usecase.user.UserRegistryUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletCreateUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDepositUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletDetailsUseCase
import com.iobuilders.bank.poc.domain.service.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class UseCaseConfiguration {

    @Bean
    fun createUserUseCase(
        userService: UserService,
        passwordEncoder: BCryptPasswordEncoder
    ): UserRegistryUseCase = UserRegistryUseCase(userService, passwordEncoder)

    @Bean
    fun findUserUseCase(
        userService: UserService
    ): UserDetailsUseCase = UserDetailsUseCase(userService)

    @Bean
    fun createWalletUseCase(
        userService: UserService, walletService: WalletService
    ): WalletCreateUseCase = WalletCreateUseCase(userService, walletService)

    @Bean
    fun walletDetailsUseCase(
        walletService: WalletService,
        movementService: MovementService,
        userService: UserService,
        amlValidationService: AmlValidationService
    ): WalletDetailsUseCase = WalletDetailsUseCase(walletService, movementService, userService, amlValidationService)

    @Bean
    fun walletDepositUseCase(
        walletService: WalletService, movementService: MovementService
    ): WalletDepositUseCase = WalletDepositUseCase(walletService, movementService)

    @Bean
    fun internalTransferUSeCase(
        walletService: WalletService,
        movementService: MovementService,
        transferService: TransferService,
        amlValidationService: AmlValidationService
    ): InternalTransferUseCase =
        InternalTransferUseCase(walletService, movementService, transferService, amlValidationService)
}
