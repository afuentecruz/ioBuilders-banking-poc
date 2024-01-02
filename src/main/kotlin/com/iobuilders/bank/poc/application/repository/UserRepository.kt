package com.iobuilders.bank.poc.application.repository

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.infrastructure.rest.dto.RegisterUserRequest

interface UserRepository {
    fun findAllUsers(): List<User>
    fun createUser(registerUserRequest: RegisterUserRequest): User
}
