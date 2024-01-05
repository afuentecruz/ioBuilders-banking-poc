package com.iobuilders.bank.poc.utils

import com.iobuilders.bank.poc.domain.User

fun User.Companion.testData(
    userId: Long? = 1,
    username: String = "usernameTest",
    password: String = "passwordTest"
) = User(id = userId, username = username, password = password)
