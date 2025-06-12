package com.petreli.application.usecases.funds;

import com.petreli.application.domain.audit.TransactionId;
import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.domain.funds.TransferData;
import com.petreli.application.dto.TransferFundsDTO;
import com.petreli.application.exception.ValidationException;
import com.petreli.application.repository.FundsRepository;
import com.petreli.application.repository.WalletRepository;
import com.petreli.application.usecases.UnitUseCase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransferFundsUseCase extends UnitUseCase<TransferFundsUseCase.Input> {

    private final FundsRepository fundsRepository;
    private final WalletRepository walletRepository;

    public TransferFundsUseCase(
          final FundsRepository fundsRepository,
          final WalletRepository walletRepository) {
        this.fundsRepository = Objects.requireNonNull(fundsRepository);
        this.walletRepository = Objects.requireNonNull(walletRepository);
    }

    @Override
    public void execute(TransferFundsUseCase.Input input) {
        final var fromWallet = walletRepository.walletOfAccountNumber(input.fromAccountNumber())
              .orElseThrow(() -> new ValidationException("From account not found"));
        final var toWallet = walletRepository.walletOfAccountNumber(input.toAccountNumber())
              .orElseThrow(() -> new ValidationException("To account not found"));

        if (input.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Transfer amount must be greater than zero");
        }
        if (fromWallet.balance().value().compareTo(input.amount()) < 0) {
            throw new ValidationException("Insufficient funds in the from account");
        }

        fromWallet.withdraw(input.amount());
        toWallet.addFunds(input.amount());

        final var transactionId = TransactionId.unique();
        final var transferDate = LocalDateTime.now();

        final var fromBalanceHistory = BalanceHistory.newBalanceHistory(
              fromWallet.walletId(),
              transactionId,
              input.fromAccountNumber,
              input.amount.negate(),
              transferDate
        );

        final var toBalanceHistory = BalanceHistory.newBalanceHistory(
              toWallet.walletId(),
              transactionId,
              input.toAccountNumber,
              input.amount,
              transferDate
        );

        final var transferData = TransferData.newTransferData(
              fromWallet.walletId(),
              toWallet.walletId(),
              fromBalanceHistory.balanceHistoryId(),
              toBalanceHistory.balanceHistoryId(),
              transactionId,
              input.amount(),
              transferDate
        );

        final var transferFunds = new TransferFundsDTO(
              fromWallet, toWallet, fromBalanceHistory, toBalanceHistory, transferData
        );

        fundsRepository.transferFunds(transferFunds);
    }

    public record Input(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount) {

    }
}
