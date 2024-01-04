package com.iobuilders.bank.poc.application.rest.response.user

import com.iobuilders.bank.poc.domain.User

data class UserResponse(
    val id: Long, val username: String
) {
    companion object
}

fun UserResponse.Companion.fromDomain(user: User) =
    UserResponse(id = user.id!!, username = user.username)
