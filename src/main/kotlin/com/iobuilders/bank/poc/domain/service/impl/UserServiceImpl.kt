package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.service.UserService
import mu.KotlinLogging

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    private val logger = KotlinLogging.logger {}

    override fun findAll(): List<User> = userRepository.findAllUsers()

    override fun findUser(userId: Long): User =
        userRepository.findUserById(userId)

    override fun createUser(user: User): User {
        logger.info { "creating user ${user.username}" }
        return userRepository.createUser(user).also {
            logger.info { "user ${it.username} successfully created with id ${it.id}" }
        }
    }

}
