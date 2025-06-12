package com.petreli.infrastructure.jpa.entities;

import com.petreli.application.domain.audit.TransactionId;
import com.petreli.application.domain.balance.BalanceHistory;
import com.petreli.application.domain.balance.BalanceHistoryId;
import com.petreli.application.domain.wallet.WalletId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "BalanceHistory")
@Table(name = "balance_history", schema = "wallet_service")
public class BalanceHistoryEntity {

    @Id
    private UUID id;
    private UUID walletId;
    private UUID transactionId;
    private Long accountNumber;
    private BigDecimal value;
    private LocalDateTime createdAt;

    public BalanceHistoryEntity() {
    }

    public BalanceHistoryEntity(
          final UUID id,
          final UUID walletId,
          final UUID transactionId,
          final Long accountNumber,
          final BigDecimal value,
          final LocalDateTime createdAt
    ) {
        this.id = id;
        this.walletId = walletId;
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.value = value;
        this.createdAt = createdAt;
    }

    public static BalanceHistoryEntity of(final BalanceHistory balanceHistory) {
        return new BalanceHistoryEntity(
              UUID.fromString(balanceHistory.balanceHistoryId().value()),
              UUID.fromString(balanceHistory.walletId().value()),
              UUID.fromString(balanceHistory.transactionId().value()),
              balanceHistory.accountNumber().value(),
              balanceHistory.value().value(),
              balanceHistory.date()
        );
    }

    public BalanceHistory toBalanceHistory() {
        return new BalanceHistory(
              BalanceHistoryId.with(this.id.toString()),
              WalletId.with(this.walletId.toString()),
              TransactionId.with(this.transactionId.toString()),
              this.accountNumber,
              value,
              createdAt
        );
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BalanceHistoryEntity that = (BalanceHistoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
