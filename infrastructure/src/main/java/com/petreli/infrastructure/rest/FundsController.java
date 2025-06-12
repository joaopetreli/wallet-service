package com.petreli.infrastructure.rest;

import com.petreli.application.exception.ValidationException;
import com.petreli.application.usecases.funds.DepositFundsUseCase;
import com.petreli.application.usecases.funds.TransferFundsUseCase;
import com.petreli.application.usecases.funds.WithdrawFundsUseCase;
import com.petreli.infrastructure.dtos.DepositFundsDTO;
import com.petreli.infrastructure.dtos.TransferFundsDTO;
import com.petreli.infrastructure.dtos.WithdrawFundsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "funds")
@AllArgsConstructor
public class FundsController {

    private final DepositFundsUseCase depositFundsUseCase;
    private final WithdrawFundsUseCase withdrawFundsUseCase;
    private final TransferFundsUseCase transferFundsUseCase;

    @PostMapping(value = "/deposit")
    public ResponseEntity<?> depositFunds(@RequestBody DepositFundsDTO dto) {
        try {
            depositFundsUseCase.execute(new DepositFundsUseCase.Input(dto.accountNumber(), dto.amount()));

            return ResponseEntity.accepted().build();
        } catch (ValidationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<?> withdrawFunds(@RequestBody WithdrawFundsDTO dto) {
        try {
            withdrawFundsUseCase.execute(new WithdrawFundsUseCase.Input(dto.accountNumber(), dto.amount()));

            return ResponseEntity.accepted().build();
        } catch (ValidationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<?> transferFunds(@RequestBody TransferFundsDTO dto) {
        try {
            transferFundsUseCase
                  .execute(new TransferFundsUseCase.Input(dto.fromAccountNumber(), dto.toAccountNumber(), dto.amount()));

            return ResponseEntity.accepted().build();
        } catch (ValidationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
