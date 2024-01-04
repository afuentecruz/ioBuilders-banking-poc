package com.iobuilders.bank.poc.application.rest.request.wallet

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import java.math.BigDecimal

data class WalletDepositRequest(
    @field:Min(1)
    val amount: BigDecimal,
    @field:NotEmpty(message = "currency cannot be empty")
    val currency: String
)
