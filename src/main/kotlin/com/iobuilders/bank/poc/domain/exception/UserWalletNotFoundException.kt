package com.iobuilders.bank.poc.domain.exception

class UserWalletNotFoundException(userId: Long) : RuntimeException("Wallet for userId $userId not found")
