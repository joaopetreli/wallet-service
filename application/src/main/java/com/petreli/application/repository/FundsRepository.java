package com.petreli.application.repository;

import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.dto.TransferFundsDTO;
import com.petreli.application.domain.wallet.Wallet;

public interface FundsRepository {
    void createTransaction(Wallet anWallet, BalanceHistory aBalanceHistory);
    void transferFunds(TransferFundsDTO dto);
}
