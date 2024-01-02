package com.iobuilders.bank.poc.infrastructure.configuration

import com.iobuilders.bank.poc.application.repository.UserRepository
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserH2Repository
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserH2RepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {

    @Bean
    fun userRepository(userH2Repository: UserH2Repository): UserRepository =
        UserH2RepositoryImpl(userH2Repository);
}
