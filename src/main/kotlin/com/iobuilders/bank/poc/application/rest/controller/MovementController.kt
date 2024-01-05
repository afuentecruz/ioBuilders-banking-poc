package com.iobuilders.bank.poc.application.rest.controller

import com.iobuilders.bank.poc.application.rest.response.wallet.WalletMovementResponse
import com.iobuilders.bank.poc.application.usecase.movement.WalletMovementUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping("/movements")
class MovementController(
    private val walletMovementUseCase: WalletMovementUseCase
) {

    @GetMapping(path = ["/wallet/{walletId}"])
    fun walletMovements(
        principal: Principal,
        @PathVariable walletId: Long
    ): ResponseEntity<List<WalletMovementResponse>> =
        walletMovementUseCase.getWalletMovements(principal.name, walletId).let { ResponseEntity.ok(it) }

}
