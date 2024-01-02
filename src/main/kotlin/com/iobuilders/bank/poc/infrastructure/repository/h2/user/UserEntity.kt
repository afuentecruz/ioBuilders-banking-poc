package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.domain.User
import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "`user`")
class UserEntity(
    @Column(name = "`username`") val username: String,
    @Column(name = "`password`") val password: String
) : BaseEntity() {
    companion object
}

fun UserEntity.Companion.fromDomain(user: User): UserEntity =
    UserEntity(username = user.username, password = user.password)

fun UserEntity.Companion.toDomain(userEntity: UserEntity): User =
    User(id = userEntity.id, username = userEntity.username, password = userEntity.password)
