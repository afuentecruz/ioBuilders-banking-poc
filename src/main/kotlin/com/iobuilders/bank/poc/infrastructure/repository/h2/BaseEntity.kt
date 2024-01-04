package com.iobuilders.bank.poc.infrastructure.repository.h2

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(
        name = "`id`",
        updatable = false,
        insertable = false
    ) protected open var id: Long?,
    @Column(name = "`created_at`", updatable = false)
    protected open var createdAt: LocalDateTime? = null
) : Serializable {


    @Column(name = "`modified_at`", updatable = true)
    var modifiedAt: LocalDateTime? = null

    @PrePersist
    protected fun prePersist() {
        val now: LocalDateTime = LocalDateTime.now()
        if (createdAt == null) createdAt = now
        modifiedAt = now
    }

    @PreUpdate
    private fun preUpdate() {
        modifiedAt = LocalDateTime.now()
    }
}
