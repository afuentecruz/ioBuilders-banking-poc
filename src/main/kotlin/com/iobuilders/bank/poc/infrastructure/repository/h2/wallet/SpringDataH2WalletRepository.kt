package com.iobuilders.bank.poc.infrastructure.repository.h2.wallet

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataH2WalletRepository : JpaRepository<WalletEntity, Long>
