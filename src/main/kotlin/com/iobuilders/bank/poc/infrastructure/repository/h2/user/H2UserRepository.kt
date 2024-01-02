package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.domain.repository.UserRepository
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.application.rest.dto.RegisterUserRequest
import org.springframework.stereotype.Component

@Component
class H2UserRepository(private val userRepository: SpringDataH2UserRepository) : UserRepository {

    override fun findAllUsers(): List<User> {
        return userRepository.findAll().let { entities ->
            entities.map { entity ->
                User(
                    id = entity.id!!,
                    username = entity.username,
                    password = entity.password
                )
            }
        }
    }

    override fun createUser(registerUserRequest: RegisterUserRequest): User {
        return userRepository.save(
            UserEntity(
                username = registerUserRequest.username,
                password = registerUserRequest.password
            )
        )
            .let {
                User(
                    id = it.id!!,
                    username = it.username, password = it.password
                )
            }
    }
}
