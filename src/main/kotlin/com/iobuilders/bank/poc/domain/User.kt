package com.iobuilders.bank.poc.domain

data class User(
    val id: Long? = null,
    val username: String,
    val password: String
) {
    companion object
}
