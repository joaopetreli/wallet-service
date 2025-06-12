package com.petreli.application.usecases.funds;

import com.petreli.application.domain.audit.TransactionId;
import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.exception.ValidationException;
import com.petreli.application.repository.FundsRepository;
import com.petreli.application.repository.WalletRepository;
import com.petreli.application.usecases.UnitUseCase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class WithdrawFundsUseCase extends UnitUseCase<WithdrawFundsUseCase.Input> {

    private final FundsRepository fundsRepository;
    private final WalletRepository walletRepository;

    public WithdrawFundsUseCase(
          final FundsRepository fundsRepository,
          final WalletRepository walletRepository) {
        this.fundsRepository = Objects.requireNonNull(fundsRepository);
        this.walletRepository = Objects.requireNonNull(walletRepository);
    }

    @Override
    public void execute(Input input) {
        final var anWallet = walletRepository.walletOfAccountNumber(input.accountNumber)
                .orElseThrow(() -> new ValidationException("Wallet not found"));
        if (anWallet.balance().value().compareTo(input.amount().abs()) < 0) {
            throw new ValidationException("Insufficient funds");
        }
        if (input.amount().compareTo(BigDecimal.ZERO) >= 0) {
            throw new ValidationException("Amount must be negative");
        }

        anWallet.withdraw(input.amount());

        final var aBalanceHistory = BalanceHistory.newBalanceHistory(
              anWallet.walletId(),
              TransactionId.unique(),
              input.accountNumber(),
              input.amount(),
              LocalDateTime.now());

        fundsRepository.createTransaction(anWallet, aBalanceHistory);
    }

    public record Input(Long accountNumber, BigDecimal amount) {
    }
}
