package com.iobuilders.bank.poc.domain.exception

class SameWalletsException : RuntimeException("Origin and destination Wallet cannot be the same")
