package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.exception.UsernameNotFoundException
import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.service.UserService

class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {

    override fun findAll(): List<User> = userRepository.findAllUsers()

    override fun findUser(userId: Long): User =
        userRepository.findUserById(userId)

    override fun findUsername(username: String): User =
        userRepository.findUsername(username) ?: throw UsernameNotFoundException(username)

    override fun createUser(user: User): User = userRepository.createUser(user)

}
