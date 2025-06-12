package com.petreli.application.domain.funds;

import com.petreli.application.domain.audit.TransactionId;
import com.petreli.application.domain.balance.BalanceHistoryId;
import com.petreli.application.domain.wallet.Balance;
import com.petreli.application.domain.wallet.WalletId;
import com.petreli.application.exception.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferData {

    private final TransferDataId transferDataId;
    private WalletId fromWalletId;
    private WalletId toWalletId;
    private BalanceHistoryId fromBalanceHistoryId;
    private BalanceHistoryId toBalanceHistoryId;
    private TransactionId transactionId;
    private Balance amount;
    private LocalDateTime date;

    public TransferData(
          final TransferDataId transferDataId,
          final WalletId fromWalletId,
          final WalletId toWalletId,
          final BalanceHistoryId fromBalanceHistoryId,
          final BalanceHistoryId toBalanceHistoryId,
          final TransactionId transactionId,
          final BigDecimal amount,
          final LocalDateTime date
    ) {
        this.transferDataId = transferDataId;
        this.setFromWalletId(fromWalletId);
        this.setToWalletId(toWalletId);
        this.setFromBalanceHistoryId(fromBalanceHistoryId);
        this.setToBalanceHistoryId(toBalanceHistoryId);
        this.setTransactionId(transactionId);
        this.setAmount(amount);
        this.setDate(date);
    }

    public static TransferData newTransferData(
          final WalletId fromWalletId,
          final WalletId toWalletId,
          final BalanceHistoryId fromBalanceHistoryId,
          final BalanceHistoryId toBalanceHistoryId,
          final TransactionId transactionId,
          final BigDecimal amount,
          final LocalDateTime date
    ) {
        return new TransferData(
              TransferDataId.unique(),
              fromWalletId,
              toWalletId,
              fromBalanceHistoryId,
              toBalanceHistoryId,
              transactionId,
              amount,
              date
        );
    }

    public TransferDataId transferDataId() {
        return transferDataId;
    }

    public WalletId fromWalletId() {
        return fromWalletId;
    }

    public WalletId toWalletId() {
        return toWalletId;
    }

    public BalanceHistoryId fromBalanceHistoryId() {
        return fromBalanceHistoryId;
    }

    public BalanceHistoryId toBalanceHistoryId() {
        return toBalanceHistoryId;
    }

    public TransactionId transactionId() {
        return transactionId;
    }

    public Balance amount() {
        return amount;
    }

    public LocalDateTime date() {
        return date;
    }

    public void setFromWalletId(WalletId fromWalletId) {
        if (fromWalletId == null) {
            throw new ValidationException("From wallet id cannot be null");
        }
        this.fromWalletId = fromWalletId;
    }

    public void setToWalletId(WalletId toWalletId) {
        if (toWalletId == null) {
            throw new ValidationException("To wallet id cannot be null");
        }
        this.toWalletId = toWalletId;
    }

    public void setFromBalanceHistoryId(
          BalanceHistoryId fromBalanceHistoryId) {
        if (fromBalanceHistoryId == null) {
            throw new ValidationException("From balance history id cannot be null");
        }
        this.fromBalanceHistoryId = fromBalanceHistoryId;
    }

    public void setToBalanceHistoryId(
          BalanceHistoryId toBalanceHistoryId) {
        if (toBalanceHistoryId == null) {
            throw new ValidationException("To balance history id cannot be null");
        }
        this.toBalanceHistoryId = toBalanceHistoryId;
    }

    public void setTransactionId(TransactionId transactionId) {
        if (transactionId == null) {
            throw new ValidationException("Transaction id cannot be null");
        }
        this.transactionId = transactionId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = new Balance(amount);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
