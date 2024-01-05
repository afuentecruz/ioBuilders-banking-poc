package com.iobuilders.bank.poc.utils;

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.MoneyCurrency
import java.math.BigDecimal

fun Money.Companion.testData(): Money =
    Money(amount = BigDecimal.TEN, currency = MoneyCurrency.EUR)
