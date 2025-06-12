package com.petreli.application.repository;

import com.petreli.application.domain.balance.BalanceHistory;
import java.time.LocalDateTime;
import java.util.List;

public interface BalanceHistoryRepository {
    List<BalanceHistory> historicalBalanceOfAccountNumber(
          Long accountNumber,
          LocalDateTime start,
          LocalDateTime end);
}
