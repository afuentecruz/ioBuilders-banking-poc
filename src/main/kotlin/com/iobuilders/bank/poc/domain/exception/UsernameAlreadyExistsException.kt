package com.iobuilders.bank.poc.domain.exception

class UsernameAlreadyExistsException(username: String) : RuntimeException("Username $username already exists")

{
}
