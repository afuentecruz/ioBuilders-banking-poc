package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.service.user.UserService
import com.iobuilders.bank.poc.infrastructure.rest.dto.RegisterUserRequest

class RegisterUserUseCase(private val userService: UserService) {
    fun registerUser(registerUserRequest: RegisterUserRequest) = registerUserRequest.let {
        userService.createUser(it)
    }
}
