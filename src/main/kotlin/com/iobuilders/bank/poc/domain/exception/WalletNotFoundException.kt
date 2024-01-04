package com.iobuilders.bank.poc.domain.exception

class WalletNotFoundException(walletId: Long) :
    RuntimeException("Wallet with id $walletId not found")
