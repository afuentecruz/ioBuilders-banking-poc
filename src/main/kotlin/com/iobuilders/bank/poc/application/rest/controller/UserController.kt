package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.request.user.RegisterUserRequest
import com.iobuilders.bank.poc.application.rest.response.user.UserResponse
import com.iobuilders.bank.poc.application.usecase.user.UserDetailsUseCase
import com.iobuilders.bank.poc.application.usecase.user.UserRegistryUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping(path = ["/users"])
class UserController(val registerUserUseCase: UserRegistryUseCase, val findUserUseCase: UserDetailsUseCase) {

    @GetMapping
    fun findAllUsers(principal: Principal): ResponseEntity<List<UserResponse>> = run {
        findUserUseCase.findAllUsers().let { ResponseEntity.ok(it) }
    }

    @PostMapping(path = ["/registry"])
    fun createUser(@Valid @RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<UserResponse> =
        registerUserUseCase.registerUser(registerUserRequest).let { ResponseEntity.ok(it) }
}
