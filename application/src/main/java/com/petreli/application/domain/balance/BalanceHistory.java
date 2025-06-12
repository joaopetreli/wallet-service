package com.petreli.application.domain.balance;

import com.petreli.application.domain.audit.TransactionId;
import com.petreli.application.domain.wallet.AccountNumber;
import com.petreli.application.domain.wallet.Balance;
import com.petreli.application.domain.wallet.WalletId;
import com.petreli.application.exception.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceHistory {

    private final BalanceHistoryId balanceHistoryId;
    private WalletId walletId;
    private TransactionId transactionId;
    private AccountNumber accountNumber;
    private Balance value;
    private LocalDateTime date;

    public BalanceHistory(
          final BalanceHistoryId balanceHistoryId,
          final WalletId walletId,
          final TransactionId transactionId,
          final Long accountNumber,
          final BigDecimal value,
          final LocalDateTime date
    ) {
        this.balanceHistoryId = balanceHistoryId;
        this.setWalletId(walletId);
        this.setTransactionId(transactionId);
        this.setAccountNumber(accountNumber);
        this.setValue(value);
        this.setDate(date);
    }

    public static BalanceHistory newBalanceHistory(
          final WalletId walletId,
          final TransactionId transactionId,
          final Long accountNumber,
          final BigDecimal value,
          final LocalDateTime date) {
        return new BalanceHistory(BalanceHistoryId.unique(), walletId, transactionId, accountNumber, value, date);
    }

    public BalanceHistoryId balanceHistoryId() {
        return balanceHistoryId;
    }

    public WalletId walletId() {
        return walletId;
    }

    public TransactionId transactionId() {
        return transactionId;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public Balance value() {
        return value;
    }

    public LocalDateTime date() {
        return date;
    }

    public void setWalletId(WalletId walletId) {
        if (walletId == null) {
            throw new ValidationException("Invalid wallet id for BalanceHistory");
        }
        this.walletId = walletId;
    }

    public void setTransactionId(TransactionId transactionId) {
        if (transactionId == null) {
            throw new ValidationException("Invalid transaction id for BalanceHistory");
        }
        this.transactionId = transactionId;
    }

    private void setAccountNumber(Long accountNumber) {
        this.accountNumber = new AccountNumber(accountNumber);
    }

    public void setValue(BigDecimal value) {
        this.value = new Balance(value);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
