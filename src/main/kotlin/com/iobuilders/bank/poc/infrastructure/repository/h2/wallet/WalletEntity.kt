package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserEntity
import jakarta.persistence.*


@Entity
@Table(name = "`wallet`")
class WalletEntity(
    public override var id: Long? = null,
    @Column(name = "`amount`") val amount: Float,
    @Column(name = "`currency`") val currency: String,
    @ManyToOne(cascade = [CascadeType.MERGE]) val user: UserEntity
) : BaseEntity(id = id) {
    companion object
}
