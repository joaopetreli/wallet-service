package com.petreli.application.repository;

import com.petreli.application.domain.wallet.Wallet;
import java.util.Optional;

public interface WalletRepository {

    Wallet create(Wallet wallet);
    Optional<Wallet> walletOfAccountNumber(Long accountNumber);
}
