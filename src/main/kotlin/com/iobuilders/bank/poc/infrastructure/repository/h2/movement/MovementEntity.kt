package com.iobuilders.bank.poc.infrastructure.repository.h2.movement

import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.wallet.WalletEntity
import jakarta.persistence.*


@Entity
@Table(name = "`movement`")
class MovementEntity(
    public override var id: Long? = null,
    @Column(name = "`type`", nullable = false) val movementType: String,
    @Column(name = "`currency`", nullable = false) val currency: String,
    @Column(name = "`amount`", nullable = false) val amount: Float,
    @ManyToOne(cascade = [CascadeType.MERGE]) val originWallet: WalletEntity? = null,
    @ManyToOne(cascade = [CascadeType.MERGE]) val destinationWallet: WalletEntity? = null,
    @ManyToOne val generatedBy: UserEntity? = null 
) : BaseEntity(id = id) {
    companion object
}
