package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.rest.response.user.UserResponse
import com.iobuilders.bank.poc.application.rest.response.user.toResponse
import com.iobuilders.bank.poc.domain.service.UserService

class UserDetailsUseCase(private val userService: UserService) {
    fun findAllUsers(): List<UserResponse> = userService.findAll().map { UserResponse.toResponse(it) }
}
