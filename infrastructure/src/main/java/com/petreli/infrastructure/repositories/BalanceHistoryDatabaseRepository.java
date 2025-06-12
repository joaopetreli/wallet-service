package com.petreli.infrastructure.repositories;

import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.repository.BalanceHistoryRepository;
import com.petreli.infrastructure.jpa.entities.BalanceHistoryEntity;
import com.petreli.infrastructure.jpa.repository.BalanceHistoryJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BalanceHistoryDatabaseRepository implements BalanceHistoryRepository {

    private final BalanceHistoryJpaRepository repository;

    @Override
    public List<BalanceHistory> historicalBalanceOfAccountNumber(
          Long accountNumber,
          LocalDateTime start,
          LocalDateTime end
    ) {
        Objects.requireNonNull(accountNumber, "Account number must not be null");
        Objects.requireNonNull(start, "Start date must not be null");
        Objects.requireNonNull(end, "End date must not be null");

        return repository.findByAccountNumberAndCreatedAtBetween(accountNumber, start, end)
              .stream()
              .map(BalanceHistoryEntity::toBalanceHistory)
              .toList();
    }
}
