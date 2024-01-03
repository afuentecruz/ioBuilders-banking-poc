package com.iobuilders.bank.poc.domain.exception

class UserNotFoundException(userId: Long) : RuntimeException("User with id $userId not found")
