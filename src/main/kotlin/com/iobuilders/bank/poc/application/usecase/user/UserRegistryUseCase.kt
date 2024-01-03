package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.rest.request.user.RegisterUserRequest
import com.iobuilders.bank.poc.application.rest.request.user.toDomain
import com.iobuilders.bank.poc.application.rest.response.user.UserResponse
import com.iobuilders.bank.poc.application.rest.response.user.toResponse
import com.iobuilders.bank.poc.domain.service.UserService

class UserRegistryUseCase(private val userService: UserService) {

    fun registerUser(registerUserRequest: RegisterUserRequest): UserResponse =
        registerUserRequest.toDomain().let {
            UserResponse.toResponse(userService.createUser(it))
        }
}
