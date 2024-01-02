package com.iobuilders.bank.poc.application.service.user

import com.iobuilders.bank.poc.application.repository.UserRepository
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.infrastructure.rest.dto.RegisterUserRequest

class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAllUsers()

    fun createUser(registerUserRequest: RegisterUserRequest) = userRepository.createUser(registerUserRequest)
}
