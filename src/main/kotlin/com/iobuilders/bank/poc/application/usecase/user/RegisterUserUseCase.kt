package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.rest.request.RegisterUserRequest
import com.iobuilders.bank.poc.application.rest.request.toDomain
import com.iobuilders.bank.poc.application.rest.response.UserResponse
import com.iobuilders.bank.poc.application.rest.response.fromDomain
import com.iobuilders.bank.poc.domain.service.UserService

class RegisterUserUseCase(private val userService: UserService) {

    fun registerUser(registerUserRequest: RegisterUserRequest): UserResponse =
        registerUserRequest.toDomain().let {
            UserResponse.fromDomain(userService.createUser(it))
        }
}
