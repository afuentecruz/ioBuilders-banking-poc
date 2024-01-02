package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.application.repository.UserRepository
import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.infrastructure.rest.dto.RegisterUserRequest
import org.springframework.context.annotation.Lazy

class UserH2RepositoryImpl(@Lazy val userH2Repository: UserH2Repository) : UserRepository {
    override fun findAllUsers(): List<User> {
        return userH2Repository.findAll().let { entities ->
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
        return userH2Repository.save(
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
