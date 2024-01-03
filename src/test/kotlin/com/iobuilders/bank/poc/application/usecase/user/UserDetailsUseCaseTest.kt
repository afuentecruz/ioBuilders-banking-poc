package com.iobuilders.bank.poc.application.usecase.user

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.service.UserService
import com.iobuilders.bank.poc.utils.userTestData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserDetailsUseCaseTest {

    private val userService: UserService = mockk();
    private val userDetailUseCase: UserDetailsUseCase = UserDetailsUseCase(userService)

    @Test
    fun whenFindAllUsers_shouldCallUserService_thenReturnAllUserResponseList() {
        // given
        val user: User = User.userTestData()
        val userList: List<User> = listOf(user)
        every { userService.findAll() } returns userList
        // when
        val result = userDetailUseCase.findAllUsers()
        // then
        Assertions.assertEquals(user.id, result.first().id)
        Assertions.assertEquals(user.username, result.first().username)
        verify(exactly = 1) { userService.findAll() }
    }

    @Test
    fun whenFindAllUsersWithNoUsers_shouldCallUserService_thenReturnEmptyList() {
        // given
        val userList: List<User> = emptyList()
        every { userService.findAll() } returns userList
        // when
        val result = userDetailUseCase.findAllUsers()
        // then
        Assertions.assertTrue(result.isEmpty())
        verify(exactly = 1) { userService.findAll() }
    }

}
