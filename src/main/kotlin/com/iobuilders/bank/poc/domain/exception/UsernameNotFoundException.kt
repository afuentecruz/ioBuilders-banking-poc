package com.iobuilders.bank.poc.domain.exception

class UsernameNotFoundException(username: String) : RuntimeException("User with username $username not found")
