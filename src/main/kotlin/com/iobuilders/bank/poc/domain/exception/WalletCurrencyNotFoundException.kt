package com.iobuilders.bank.poc.domain.exception

class WalletCurrencyNotFoundException(walletId: Long, currency: String) :
    RuntimeException("Wallet with id $walletId and currency $currency not found")
