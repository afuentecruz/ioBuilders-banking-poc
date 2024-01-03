package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.exception.UserNotFoundException
import com.iobuilders.bank.poc.domain.exception.UsernameAlreadyExistsException
import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.service.impl.UserServiceImpl
import com.iobuilders.bank.poc.utils.userTestData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserServiceTest {

    private var userRepository: UserRepository = mockk()

    private val userService: UserService = UserServiceImpl(userRepository)

    @Test
    fun whenFindAllUsersButNoUsersInRepository_shouldReturnEmptyList() {
        // given
        every { userRepository.findAllUsers() } returns (emptyList())
        // when
        val result = userService.findAll()
        // then
        Assertions.assertTrue(result.isEmpty())
    }

    @Test
    fun whenFindAllUsers_shouldReturnTheUsers() {
        // given
        val user: User = User.userTestData()
        every { userRepository.findAllUsers() } returns (listOf(user))
        // when
        val result = userService.findAll()
        // then
        Assertions.assertFalse(result.isEmpty())
        Assertions.assertEquals(user.id, result.first().id)
        Assertions.assertEquals(user.username, result.first().username)
        Assertions.assertEquals(user.password, result.first().password)
    }

    @Test
    fun whenFindUserOfNonExistingUser_shouldThrowException() {
        // given
        val userId = 1L
        every { userRepository.findUserById(userId) } throws UserNotFoundException(userId)
        // when
        val result = Assertions.assertThrows(UserNotFoundException::class.java) { userService.findUser(userId) }
        // then
        Assertions.assertEquals("User with id $userId not found", result.message)
    }

    @Test
    fun whenFindUser_shouldReturnTheUser() {
        // given
        val userId: Long = 1L
        val user: User = User.userTestData(userId = userId)
        every { userRepository.findUserById(userId) } returns (user)
        // when
        val result = userService.findUser(userId)
        // then
        Assertions.assertEquals(user.id, result.id)
        Assertions.assertEquals(user.username, result.username)
        Assertions.assertEquals(user.password, result.password)
    }

    @Test
    fun whenCreateUser_shouldReturnTheCreatedUser() {
        // given
        val user: User = User.userTestData()
        every { userRepository.createUser(user) } returns (user)
        // when
        val result = userService.createUser(user)
        // then
        Assertions.assertEquals(user.id, result.id)
        Assertions.assertEquals(user.username, result.username)
        Assertions.assertEquals(user.password, result.password)
    }

    @Test
    fun whenCreateUserButDataIntegrityException_shouldThrowDomainException() {
        // given
        val user: User = User.userTestData()
        every { userRepository.createUser(user) } throws (UsernameAlreadyExistsException(user.username))
        // when
        val result =
            Assertions.assertThrows(UsernameAlreadyExistsException::class.java) { userService.createUser(user) }
        // then
        Assertions.assertEquals("Username ${user.username} already exists", result.message)
    }

}
