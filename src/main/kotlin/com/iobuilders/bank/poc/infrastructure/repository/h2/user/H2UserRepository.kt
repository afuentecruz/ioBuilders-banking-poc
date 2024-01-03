package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.domain.exception.UserNotFoundException
import com.iobuilders.bank.poc.domain.repository.UserRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrElse

@Component
class H2UserRepository(private val userRepository: SpringDataH2UserRepository) : UserRepository {

    override fun findAllUsers(): List<User> = userRepository.findAll().let { userEntities ->
        userEntities.map {
            UserEntity.toDomain(it)
        }
    }

    override fun createUser(user: User): User = userRepository.save(UserEntity.toEntity(user)).let {
        UserEntity.toDomain(it)
    }

    override fun findUserById(id: Long): User =
        userRepository.findById(id).getOrElse { throw UserNotFoundException("User with id $id not found") }
            .let { UserEntity.toDomain(it) }
}
