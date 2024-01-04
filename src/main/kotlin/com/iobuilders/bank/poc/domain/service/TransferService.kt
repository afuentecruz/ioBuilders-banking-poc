package com.iobuilders.bank.poc.domain.service

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Wallet

interface TransferService {
    fun doTransfer(origin: Wallet, destination: Wallet, money: Money)
}
