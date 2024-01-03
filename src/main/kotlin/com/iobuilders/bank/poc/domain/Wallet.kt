package com.iobuilders.bank.poc.domain

data class Wallet(val id: Long? = null, val user: User, val balance: Money)
