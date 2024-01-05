package com.iobuilders.bank.poc.domain.repository

import com.iobuilders.bank.poc.domain.User

interface UserRepository {
    fun findAllUsers(): List<User>
    fun findUsername(username: String): User?
    fun createUser(user: User): User
    fun findUserById(id: Long): User
}
