package com.petreli.infrastructure.rest;

import com.petreli.application.usecases.balance.RetrieveBalanceUseCase;
import com.petreli.application.usecases.balance.RetrieveBalanceUseCase.Input;
import com.petreli.application.usecases.balance.RetrieveHistoricalBalanceUseCase;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "balance")
@AllArgsConstructor
public class BalanceController {

    private final RetrieveBalanceUseCase retrieveBalanceUseCase;
    private final RetrieveHistoricalBalanceUseCase retrieveHistoricalBalanceUseCase;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> retrieveBalance(@PathVariable final Long accountNumber) {
        return retrieveBalanceUseCase.execute(new Input(accountNumber))
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{accountNumber}/historical-balance")
    public ResponseEntity<?> retrieveHistoricalBalance(
          @PathVariable final Long accountNumber,
          @RequestParam final LocalDateTime startDate,
          @RequestParam final LocalDateTime endDate) {
        return
              retrieveHistoricalBalanceUseCase.execute(
                          new RetrieveHistoricalBalanceUseCase.Input(accountNumber, startDate, endDate))
                    .map(ResponseEntity::ok)
                    .orElseGet(ResponseEntity.notFound()::build);

    }
}
