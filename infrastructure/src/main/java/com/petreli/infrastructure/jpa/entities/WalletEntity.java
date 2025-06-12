package com.petreli.infrastructure.jpa.entities;

import com.petreli.application.domain.wallet.Wallet;
import com.petreli.application.domain.wallet.WalletId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Wallet")
@Table(name = "wallet", schema = "wallet_service")
public class WalletEntity {

    @Id
    private UUID id;
    private Integer branchCode;
    private Long accountNumber;
    private BigDecimal balance;

    public WalletEntity() {
    }

    public WalletEntity(
          final UUID id,
          final Long accountNumber,
          final BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static WalletEntity of(final Wallet wallet) {
        return new WalletEntity(
              UUID.fromString(wallet.walletId().value()),
              wallet.accountNumber().value(),
              wallet.balance().value());
    }

    public Wallet toWallet() {
        return new Wallet(
              WalletId.with(this.id.toString()),
              this.accountNumber,
              this.balance);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WalletEntity that = (WalletEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
