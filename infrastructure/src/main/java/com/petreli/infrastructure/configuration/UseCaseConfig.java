package com.petreli.infrastructure.configuration;

import com.petreli.application.provider.AccountNumberProvider;
import com.petreli.application.repository.BalanceHistoryRepository;
import com.petreli.application.repository.FundsRepository;
import com.petreli.application.repository.WalletRepository;
import com.petreli.application.usecases.balance.RetrieveBalanceUseCase;
import com.petreli.application.usecases.balance.RetrieveHistoricalBalanceUseCase;
import com.petreli.application.usecases.funds.DepositFundsUseCase;
import com.petreli.application.usecases.funds.TransferFundsUseCase;
import com.petreli.application.usecases.funds.WithdrawFundsUseCase;
import com.petreli.application.usecases.wallet.CreateWalletUseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UseCaseConfig {

    private final FundsRepository fundsRepository;
    private final WalletRepository walletRepository;
    private final AccountNumberProvider accountNumberProvider;
    private final BalanceHistoryRepository balanceHistoryRepository;

    @Bean
    public CreateWalletUseCase createWalletUseCase() {
        return new CreateWalletUseCase(walletRepository, accountNumberProvider);
    }

    @Bean
    public RetrieveBalanceUseCase retrieveBalanceUseCase() {
        return new RetrieveBalanceUseCase(walletRepository);
    }

    @Bean
    public RetrieveHistoricalBalanceUseCase retrieveHistoricalBalanceUseCase() {
        return new RetrieveHistoricalBalanceUseCase(balanceHistoryRepository);
    }

    @Bean
    public DepositFundsUseCase depositFundsUseCase() {
        return new DepositFundsUseCase(fundsRepository, walletRepository);
    }

    @Bean
    public WithdrawFundsUseCase withdrawFundsUseCase() {
        return new WithdrawFundsUseCase(fundsRepository, walletRepository);
    }

    @Bean
    public TransferFundsUseCase transferFundsUseCase() {
        return new TransferFundsUseCase(fundsRepository, walletRepository);
    }
}
