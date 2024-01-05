package com.iobuilders.bank.poc.utils;

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Transfer
import com.iobuilders.bank.poc.domain.TransferStatus
import com.iobuilders.bank.poc.domain.Wallet

fun Transfer.Companion.testData(
    id: Long? = 1L,
    status: TransferStatus = TransferStatus.COMPLETED,
    origin: Wallet = Wallet.testData(),
    destination: Wallet = Wallet.testData(),
    money: Money = Money.testData()
): Transfer = Transfer(
    transferId = id, money = money, origin = origin, destination = destination, status = status
)
