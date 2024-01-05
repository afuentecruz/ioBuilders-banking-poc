package com.iobuilders.bank.poc.application.rest.request.user

import com.iobuilders.bank.poc.domain.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class RegisterUserRequest(
    @field:NotNull(message = "username is mandatory")
    @field:NotBlank(message = "username cannot be empty")
    @field:Email(message = "invalid email format")
    val username: String,
    @field:NotNull(message = "password is mandatory")
    @field:NotBlank(message = "password cannot be empty")
    var password: String
) {
    companion object
}

fun RegisterUserRequest.toDomain(): User =
    User(username = this.username, password = this.password)
