package com.iobuilders.bank.poc.application.rest.dto

import jakarta.validation.constraints.NotBlank


data class RegisterUserRequest(
    @field:NotBlank(message = "username cannot be empty")
    val username: String,
    @field:NotBlank(message = "password cannot be empty")
    val password: String
)
