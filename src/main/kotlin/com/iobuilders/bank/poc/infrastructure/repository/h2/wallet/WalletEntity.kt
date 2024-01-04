package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import com.iobuilders.bank.poc.infrastructure.repository.h2.BaseEntity
import com.iobuilders.bank.poc.infrastructure.repository.h2.user.UserEntity
import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "`wallet`")
class WalletEntity(
    public override var id: Long? = null,
    @Column(name = "`amount`", nullable = false) val amount: BigDecimal,
    @Column(name = "`currency`", nullable = false) val currency: String,
    @ManyToOne(cascade = [CascadeType.MERGE]) val user: UserEntity
) : BaseEntity(id = id) {
    companion object
}
