package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.domain.User

fun User.toEntity(): UserEntity =
    UserEntity(id = this.id, username = this.username, password = this.password)

fun UserEntity.toDomain(): User =
    User(id = this.id, username = this.username, password = this.password)
