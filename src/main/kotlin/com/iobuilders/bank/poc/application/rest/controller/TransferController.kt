package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.request.transfer.InternalTransferRequest
import com.iobuilders.bank.poc.application.usecase.transfer.InternalTransferUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping("/transfers")
class TransferController(private val internalTransferUseCase: InternalTransferUseCase) {

    @PostMapping(value = ["/internal"])
    fun internalTransfer(
        principal: Principal,
        @Valid @RequestBody internalTransferRequest: InternalTransferRequest
    ): ResponseEntity<Unit> =
        internalTransferUseCase.internalTransfer(principal.name, internalTransferRequest)
            .let { ResponseEntity.ok().build() }
}
