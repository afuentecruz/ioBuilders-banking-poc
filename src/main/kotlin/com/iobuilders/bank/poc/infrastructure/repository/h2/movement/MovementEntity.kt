package com.iobuilders.bank.poc.infrastructure.repository.h2.movement

import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.wallet.WalletEntity
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "`movement`")
class MovementEntity(
    public override var id: Long? = null,
    public override var createdAt: LocalDateTime? = null,
    @Column(name = "`type`", nullable = false) val movementType: String,
    @Column(name = "`currency`", nullable = false) val currency: String,
    @Column(name = "`amount`", nullable = false) val amount: BigDecimal,
    @ManyToOne(cascade = [CascadeType.MERGE]) val wallet: WalletEntity,
    @ManyToOne val generatedBy: UserEntity? = null
) : BaseEntity(id = id, createdAt = createdAt) {
    companion object
}
