package com.petreli.infrastructure.repositories;

import com.petreli.application.domain.wallet.Wallet;
import com.petreli.application.repository.WalletRepository;
import com.petreli.infrastructure.jpa.entities.WalletEntity;
import com.petreli.infrastructure.jpa.repository.WalletJpaRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletDatabaseRepository implements WalletRepository {

    private final WalletJpaRepository repository;

    @Override
    public Wallet create(Wallet wallet) {
        return this.repository.save(WalletEntity.of(wallet))
              .toWallet();
    }

    @Override
    public Optional<Wallet> walletOfAccountNumber(Long accountNumber) {
        return this.repository.findByAccountNumber(accountNumber)
              .map(WalletEntity::toWallet);
    }
}
