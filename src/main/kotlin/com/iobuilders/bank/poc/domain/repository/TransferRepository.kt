package com.iobuilders.bank.poc.domain.repository

import com.iobuilders.bank.poc.domain.Transfer

interface TransferRepository {
    fun save(transfer: Transfer): Transfer
}
