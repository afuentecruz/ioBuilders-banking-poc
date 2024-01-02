package com.iobuilders.bank.poc.infrastructure.rest.controller

import com.iobuilders.bank.poc.application.usecase.user.FindUserUseCase
import com.iobuilders.bank.poc.application.usecase.user.RegisterUserUseCase
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.infrastructure.rest.dto.RegisterUserRequest
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val registerUserUseCase: RegisterUserUseCase, val findUserUseCase: FindUserUseCase) {

    @GetMapping
    fun findAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(findUserUseCase.findAllUsers())

    @PostMapping
    fun createUser(@Valid @RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<User> =
        ResponseEntity.ok(registerUserUseCase.registerUser(registerUserRequest))
}
