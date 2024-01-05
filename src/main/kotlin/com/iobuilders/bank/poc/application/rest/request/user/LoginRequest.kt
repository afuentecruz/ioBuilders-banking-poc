package com.iobuilders.bank.poc.application.rest.request.user

import java.beans.ConstructorProperties

data class LoginRequest
@ConstructorProperties("username", "password")
constructor(val username: String, val password: String)
