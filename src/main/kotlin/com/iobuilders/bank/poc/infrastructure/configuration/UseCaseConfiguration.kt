package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.application.service.UserServiceImpl
import com.iobuilders.bank.poc.application.usecase.user.UserDetailsUseCase
import com.iobuilders.bank.poc.application.usecase.user.UserRegistryUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.WalletCreateUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun createUserUseCase(userService: UserServiceImpl): UserRegistryUseCase = UserRegistryUseCase(userService)

    @Bean
    fun findUserUseCase(userService: UserServiceImpl): UserDetailsUseCase = UserDetailsUseCase(userService)

    @Bean
    fun createWalletUseCase(userService: UserServiceImpl): WalletCreateUseCase = WalletCreateUseCase(userService)
}
