package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.application.usecase.user.FindUserUseCase
import com.iobuilders.bank.poc.application.usecase.user.RegisterUserUseCase
import com.iobuilders.bank.poc.application.usecase.wallet.CreateWalletUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun createUserUseCase(userService: UserService): RegisterUserUseCase = RegisterUserUseCase(userService)

    @Bean
    fun findUserUseCase(userService: UserService): FindUserUseCase = FindUserUseCase(userService)

    @Bean
    fun createWalletUseCase(userService: UserService): CreateWalletUseCase = CreateWalletUseCase(userService)
}
