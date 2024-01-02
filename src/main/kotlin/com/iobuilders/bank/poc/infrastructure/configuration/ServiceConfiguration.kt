package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.application.repository.UserRepository
import com.iobuilders.bank.poc.application.service.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

    @Bean
    fun userService(userRepository: UserRepository): UserService = UserService(userRepository)
}
