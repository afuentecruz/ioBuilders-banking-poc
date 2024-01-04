package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.domain.repository.MovementRepository
import com.iobuilders.bank.poc.domain.repository.TransferRepository
import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.repository.WalletRepository
import com.iobuilders.bank.poc.domain.service.impl.MovementServiceImpl
import com.iobuilders.bank.poc.domain.service.impl.TransferServiceImpl
import com.iobuilders.bank.poc.domain.service.impl.UserServiceImpl
import com.iobuilders.bank.poc.domain.service.impl.WalletServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

    @Bean
    fun userServiceImpl(
        userRepository: UserRepository
    ): UserServiceImpl = UserServiceImpl(userRepository)

    @Bean
    fun walletServiceImpl(
        walletRepository: WalletRepository
    ): WalletServiceImpl = WalletServiceImpl(walletRepository)

    @Bean
    fun movementServiceImpl(
        movementRepository: MovementRepository
    ): MovementServiceImpl = MovementServiceImpl(movementRepository)

    @Bean
    fun transferServiceImpl(
        transferRepository: TransferRepository
    ): TransferServiceImpl = TransferServiceImpl(transferRepository)
}
