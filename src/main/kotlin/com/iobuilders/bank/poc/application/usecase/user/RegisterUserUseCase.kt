package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.application.rest.dto.RegisterUserRequest

class RegisterUserUseCase(private val userService: UserService) {
    fun registerUser(registerUserRequest: RegisterUserRequest) = registerUserRequest.let {
        userService.createUser(it)
    }
}
