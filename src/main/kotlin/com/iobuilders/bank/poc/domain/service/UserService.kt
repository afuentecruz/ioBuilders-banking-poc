package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.repository.UserRepository

class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAllUsers()

    fun createUser(user: User) = userRepository.createUser(user)
}
