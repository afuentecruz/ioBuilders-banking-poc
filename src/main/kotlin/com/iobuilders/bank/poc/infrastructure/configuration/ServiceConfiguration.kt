package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.service.impl.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

    @Bean
    fun userServiceImpl(userRepository: UserRepository): UserServiceImpl = UserServiceImpl(userRepository)
}
