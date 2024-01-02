package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.rest.response.UserResponse
import com.iobuilders.bank.poc.application.rest.response.fromDomain
import com.iobuilders.bank.poc.domain.service.UserService

class FindUserUseCase(private val userService: UserService) {

    fun findAllUsers(): List<UserResponse> = userService.findAll().map { UserResponse.fromDomain(it) }
}
