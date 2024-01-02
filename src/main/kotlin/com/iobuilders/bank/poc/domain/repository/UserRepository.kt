package com.iobuilders.bank.poc.domain.repository

import com.iobuilders.bank.poc.domain.User

interface UserRepository {
    fun findAllUsers(): List<User>
    fun createUser(user: User): User
}
