package com.petreli.application.usecases.balance;

import com.petreli.application.exception.ValidationException;
import com.petreli.application.repository.BalanceHistoryRepository;
import com.petreli.application.usecases.UseCase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RetrieveHistoricalBalanceUseCase
      extends UseCase<RetrieveHistoricalBalanceUseCase.Input, Optional<RetrieveHistoricalBalanceUseCase.Output>> {

    private final BalanceHistoryRepository balanceHistoryRepository;

    public RetrieveHistoricalBalanceUseCase(final BalanceHistoryRepository balanceHistoryRepository) {
        this.balanceHistoryRepository = Objects.requireNonNull(balanceHistoryRepository);
    }

    @Override
    public Optional<Output> execute(Input input) {
        if (input.start.isAfter(input.end)) {
            throw new ValidationException("Start date must be before end date");
        }
        if (ChronoUnit.DAYS.between(input.start(), input.end()) > 31) {
            throw new ValidationException("Date range must not exceed 1 month");
        }

        final var anHistoricalBalance =
              balanceHistoryRepository.historicalBalanceOfAccountNumber(
                    input.accountNumber(), input.start(), input.end());

        if (anHistoricalBalance.isEmpty()) {
            return Optional.empty();
        }

        final var anOutputBalanceHistory = anHistoricalBalance.stream()
              .map(hb -> new OutputBalanceHistory(hb.value().value(), hb.date()))
              .toList();

        return Optional.of(new Output(anHistoricalBalance.getFirst().accountNumber().value(), anOutputBalanceHistory));
    }

    public record Input(Long accountNumber, LocalDateTime start, LocalDateTime end) {
    }

    public record Output(Long accountNumber, List<OutputBalanceHistory> balanceHistories){}

    public record OutputBalanceHistory(BigDecimal amount, LocalDateTime date) {
    }
}
