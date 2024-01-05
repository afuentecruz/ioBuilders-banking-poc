package com.iobuilders.bank.poc.domain

import java.math.BigDecimal

data class Money(var amount: BigDecimal, val currency: MoneyCurrency) {
    companion object
}
