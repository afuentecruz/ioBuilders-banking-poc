package com.iobuilders.bank.poc.application.rest.request.transfer

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class InternalTransferRequest(
    @field:NotNull(message = "ordered by is mandatory")
    val orderedBy: Long,
    @field:NotNull(message = "coco")
    @field:Min(1)
    val amount: BigDecimal,
    @field:NotBlank(message = "currency cannot be empty")
    val currency: String,
    @field:NotNull(message = "origin account is mandatory")
    val from: Long,
    @field:NotNull(message = "destination account is mandatory")
    val to: Long
)
