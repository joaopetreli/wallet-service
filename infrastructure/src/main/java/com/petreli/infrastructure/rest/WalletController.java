package com.petreli.infrastructure.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.petreli.application.exception.ValidationException;
import com.petreli.application.usecases.wallet.CreateWalletUseCase;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wallets")
@AllArgsConstructor
public class WalletController {

    private final CreateWalletUseCase createWalletUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<?> create() {
        try {
            final var output = createWalletUseCase.execute();
            final var uri = URI.create("/wallets/%d/balance".formatted(output.accountNumber()));

            return ResponseEntity.created(uri).body(output);
        } catch (ValidationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


}
