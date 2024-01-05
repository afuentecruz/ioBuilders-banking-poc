package com.iobuilders.bank.poc.domain

data class Transfer(
    val transferId: Long? = null,
    val money: Money,
    val origin: Wallet,
    val destination: Wallet,
    val status: TransferStatus
){
    companion object
}
