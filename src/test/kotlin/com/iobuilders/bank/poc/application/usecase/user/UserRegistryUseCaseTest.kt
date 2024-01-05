package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.application.rest.request.user.RegisterUserRequest
import com.iobuilders.bank.poc.application.rest.request.user.toDomain
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.utils.userTestData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserRegistryUseCaseTest {

    private var userService: UserService = mockk()

    private val passwordEncoder: BCryptPasswordEncoder = mockk()

    private val userRegistryUseCase: UserRegistryUseCase = UserRegistryUseCase(userService, passwordEncoder)


    @Test
    fun whenRegisterUser_shouldCallUserService_thenReturnCreatedUser() {
        // given
        val registerUserRequest: RegisterUserRequest =
            RegisterUserRequest(username = "usernameTest", password = "passwordTest")

        val securedUserRequest: RegisterUserRequest = registerUserRequest.copy(password = "encoded-test-password")
        val securedUser: User = securedUserRequest.toDomain();
        val createdUser: User = User.userTestData()
        every { passwordEncoder.encode(registerUserRequest.password) } returns "encoded-test-password"
        every { userService.createUser(securedUser) } returns createdUser
        // when
        val result = userRegistryUseCase.registerUser(registerUserRequest)
        // then
        Assertions.assertEquals(createdUser.id, 1)
        Assertions.assertEquals(createdUser.username, result.username)
        verify(exactly = 1) { passwordEncoder.encode("passwordTest") }
        verify(exactly = 1) { userService.createUser(securedUserRequest.toDomain()) }
    }
}
