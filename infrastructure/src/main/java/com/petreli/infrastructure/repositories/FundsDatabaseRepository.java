package com.petreli.infrastructure.repositories;

import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.dto.TransferFundsDTO;
import com.petreli.application.domain.wallet.Wallet;
import com.petreli.application.repository.FundsRepository;
import com.petreli.infrastructure.jpa.entities.BalanceHistoryEntity;
import com.petreli.infrastructure.jpa.entities.TransferDataEntity;
import com.petreli.infrastructure.jpa.entities.WalletEntity;
import com.petreli.infrastructure.jpa.repository.BalanceHistoryJpaRepository;
import com.petreli.infrastructure.jpa.repository.TransferDataJpaRepository;
import com.petreli.infrastructure.jpa.repository.WalletJpaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FundsDatabaseRepository implements FundsRepository {

    private final WalletJpaRepository walletRepository;
    private final TransferDataJpaRepository transferDataRepository;
    private final BalanceHistoryJpaRepository balanceHistoryRepository;

    @Override
    @Transactional
    public void createTransaction(final Wallet wallet, final BalanceHistory balanceHistory) {
        walletRepository.save(WalletEntity.of(wallet));
        balanceHistoryRepository.save(BalanceHistoryEntity.of(balanceHistory));
    }

    @Override
    @Transactional
    public void transferFunds(final TransferFundsDTO dto) {
        walletRepository.save(WalletEntity.of(dto.fromWallet()));
        walletRepository.save(WalletEntity.of(dto.toWallet()));

        balanceHistoryRepository.save(BalanceHistoryEntity.of(dto.fromBalanceHistory()));
        balanceHistoryRepository.save(BalanceHistoryEntity.of(dto.toBalanceHistory()));

        transferDataRepository.save(TransferDataEntity.of(dto.transferData()));
    }

}
