package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.request.RegisterUserRequest
import com.iobuilders.bank.poc.application.rest.response.UserResponse
import com.iobuilders.bank.poc.application.usecase.user.FindUserUseCase
import com.iobuilders.bank.poc.application.usecase.user.RegisterUserUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val registerUserUseCase: RegisterUserUseCase, val findUserUseCase: FindUserUseCase) {

    @GetMapping
    fun findAllUsers(): ResponseEntity<List<UserResponse>> = ResponseEntity.ok(findUserUseCase.findAllUsers())

    @PostMapping(path = ["/registry"])
    fun createUser(@Valid @RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<UserResponse> =
        ResponseEntity.ok(registerUserUseCase.registerUser(registerUserRequest))
}
