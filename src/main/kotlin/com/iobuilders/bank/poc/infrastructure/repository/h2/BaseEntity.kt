package com.iobuilders.bank.poc.infrastructure.repository.h2

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "`id`", updatable = false, insertable = false)
    var id: Long? = null

    @Column(name = "`created_at`", updatable = false)
    var createdAt: LocalDateTime? = null

    @PrePersist
    protected fun prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now()
    }
}
