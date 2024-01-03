package com.iobuilders.bank.poc.application.rest.controller.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    data class ErrorMessageModel(
        var status: Int? = null,
        var message: String? = null
    )

    @ExceptionHandler
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorMessageModel> =
        ResponseEntity(
            ErrorMessageModel(status = HttpStatus.BAD_REQUEST.value(), message = ex.message),
            HttpStatus.BAD_REQUEST
        )

}
