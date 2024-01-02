package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.repository.UserRepository
import mu.KotlinLogging

class UserService(private val userRepository: UserRepository) {

    private val logger = KotlinLogging.logger {}

    fun findAll(): List<User> = userRepository.findAllUsers()

    fun createUser(user: User): User {
        logger.info { "creating user ${user.username}" }
        return userRepository.createUser(user).also {
            logger.info { "user ${it.username} successfully created with id ${it.id}" }
        }
    }

    fun findUser(userId: Long): User =
        userRepository.findUserById(userId)
}
