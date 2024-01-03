package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.domain.User

fun UserEntity.Companion.toEntity(user: User): UserEntity =
    UserEntity(username = user.username, password = user.password)

fun UserEntity.Companion.toDomain(userEntity: UserEntity): User =
    User(id = userEntity.id, username = userEntity.username, password = userEntity.password)
