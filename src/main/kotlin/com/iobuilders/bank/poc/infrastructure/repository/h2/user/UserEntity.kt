package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "`user`")
class UserEntity(
    public override var id: Long? = null,
    @Column(name = "`username`", unique = true) val username: String,
    @Column(name = "`password`") val password: String
) : BaseEntity(id) {
    companion object
}
