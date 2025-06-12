package com.petreli.application.usecases.balance;

import com.petreli.application.repository.WalletRepository;
import com.petreli.application.usecases.UseCase;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class RetrieveBalanceUseCase
      extends UseCase<RetrieveBalanceUseCase.Input, Optional<RetrieveBalanceUseCase.Output>> {

    private final WalletRepository walletRepository;

    public RetrieveBalanceUseCase(final WalletRepository walletRepository) {
        this.walletRepository = Objects.requireNonNull(walletRepository);
    }

    @Override
    public Optional<Output> execute(final Input input) {
        return walletRepository.walletOfAccountNumber(input.accountNumber)
                .map(w -> new Output(w.accountNumber().value(), w.balance().value()));
    }

    public record Input(Long accountNumber) {
    }

    public record Output(Long accountNumber, BigDecimal balance) {
    }

}
