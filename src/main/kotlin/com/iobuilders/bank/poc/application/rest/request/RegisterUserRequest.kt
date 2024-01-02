package com.iobuilders.bank.poc.application.rest.request

import com.iobuilders.bank.poc.domain.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class RegisterUserRequest(
    @field:NotBlank(message = "username cannot be empty")
    @field:Email(message = "invalid email format")
    val username: String,
    @field:NotBlank(message = "password cannot be empty")
    val password: String
) {
    companion object
}

fun RegisterUserRequest.toDomain(): User =
    User(username = this.username, password = this.password)
