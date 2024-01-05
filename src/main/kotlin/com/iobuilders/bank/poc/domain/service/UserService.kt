package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.User

interface UserService {
    fun findAll(): List<User>
    fun findUser(userId: Long): User
    fun findUsername(username: String): User
    fun createUser(user: User): User
}

