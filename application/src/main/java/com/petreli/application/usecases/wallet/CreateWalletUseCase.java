package com.petreli.application.usecases.wallet;

import com.petreli.application.domain.wallet.Wallet;
import com.petreli.application.provider.AccountNumberProvider;
import com.petreli.application.repository.WalletRepository;
import com.petreli.application.usecases.NullaryUseCase;
import java.math.BigDecimal;
import java.util.Objects;

public class CreateWalletUseCase
      extends NullaryUseCase<CreateWalletUseCase.Output> {

    private final WalletRepository walletRepository;
    private final AccountNumberProvider accountNumberProvider;

    public CreateWalletUseCase(
          final WalletRepository walletRepository,
          final AccountNumberProvider accountNumberProvider) {
        this.walletRepository = Objects.requireNonNull(walletRepository);
        this.accountNumberProvider = Objects.requireNonNull(accountNumberProvider);
    }

    @Override
    public CreateWalletUseCase.Output execute() {
        final var accountNumber = accountNumberProvider.nextNumber();
        final var aWallet =
              walletRepository.create(Wallet.newWallet(
                    accountNumber,
                    BigDecimal.ZERO));

        return new Output(
              aWallet.walletId().value(),
              aWallet.accountNumber().value(),
              aWallet.balance().value());
    }

    public record Output(String walletId, Long accountNumber, BigDecimal balance) {
    }

}