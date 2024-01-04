package com.iobuilders.bank.poc.application.rest.request.wallet

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class WalletDepositRequest(
    @field:Min(1)
    val amount: Float,
    @field:NotBlank(message = "currency cannot be empty")
    val currency: String
)
