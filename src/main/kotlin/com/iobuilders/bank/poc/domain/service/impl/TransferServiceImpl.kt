package com.iobuilders.bank.poc.domain.service.impl

import com.iobuilders.bank.poc.domain.Money
import com.iobuilders.bank.poc.domain.Transfer
import com.iobuilders.bank.poc.domain.TransferStatus
import com.iobuilders.bank.poc.domain.Wallet
import com.iobuilders.bank.poc.domain.repository.TransferRepository
import com.iobuilders.bank.poc.domain.service.TransferService

class TransferServiceImpl(
    private val transferRepository: TransferRepository
) : TransferService {

    override fun doTransfer(origin: Wallet, destination: Wallet, money: Money): Transfer {
        try {
            Transfer(
                money = money, origin = origin, destination = destination, status = TransferStatus.PENDING
            ).let {
                transferRepository.save(it).apply {
                    return transferRepository.save(this.copy(status = TransferStatus.COMPLETED))
                }
            }
        } catch (ex: Exception) {
            return transferRepository.save(
                Transfer(
                    money = money, origin = origin, destination = destination, status = TransferStatus.ERROR
                )
            )
        }
    }
}
