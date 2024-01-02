package com.iobuilders.bank.poc.infrastructure.repository.h2.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserH2Repository : JpaRepository<UserEntity, Long>

