package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.domain.User

class FindUserUseCase(private val userService: UserService){

    fun findAllUsers(): List<User> = userService.findAll()
}
