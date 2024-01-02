package com.iobuilders.bank.poc.application.rest.controller.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<String> =
        ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)

}
