package com.petreli.application.domain.wallet;

import java.math.BigDecimal;

public class Wallet {

    private final WalletId walletId;
    private AccountNumber accountNumber;
    private Balance balance;

    public Wallet(
          final WalletId walletId,
          final Long accountNumber,
          final BigDecimal balance
    ) {
        this.walletId = walletId;
        this.setAccountNumber(accountNumber);
        this.setBalance(balance);
    }

    public static Wallet newWallet(
          final Long accountNumber,
          final BigDecimal balance
    ) {
        return new Wallet(WalletId.unique(), accountNumber, balance);
    }

    public void setAccountNumber(final Long accountNumber) {
        this.accountNumber = new AccountNumber(accountNumber);
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = new Balance(balance);
    }

    public void addFunds(final BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(final BigDecimal amount) {
        this.balance = this.balance.subtract(amount.abs());
    }

    public WalletId walletId() {
        return walletId;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public Balance balance() {
        return balance;
    }
}
