package com.petreli.application.dto;

import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.domain.funds.TransferData;
import com.petreli.application.domain.wallet.Wallet;

public record TransferFundsDTO(
      Wallet fromWallet,
      Wallet toWallet,
      BalanceHistory fromBalanceHistory,
      BalanceHistory toBalanceHistory,
      TransferData transferData
) {

}
