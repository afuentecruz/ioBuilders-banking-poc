package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.rest.request.user.RegisterUserRequest
import com.iobuilders.bank.poc.application.rest.request.user.toDomain
import com.iobuilders.bank.poc.application.rest.response.user.UserResponse
import com.iobuilders.bank.poc.application.rest.response.user.fromDomain
import com.iobuilders.bank.poc.domain.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserRegistryUseCase(
    private val userService: UserService,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun registerUser(registerUserRequest: RegisterUserRequest): UserResponse =
        registerUserRequest.toDomain().let {
            val securedUser = it.copy(password = passwordEncoder.encode(it.password))
            UserResponse.fromDomain(userService.createUser(securedUser))
        }
}
