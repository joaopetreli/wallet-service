package com.petreli.infrastructure.jpa.entities;

import com.petreli.application.domain.funds.TransferData;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TransferData")
@Table(name = "transfer_data", schema = "wallet_service")
public class TransferDataEntity {

    @Id
    private UUID id;
    private UUID fromWalletId;
    private UUID toWalletId;
    private UUID fromBalanceHistoryId;
    private UUID toBalanceHistoryId;
    private UUID transactionId;
    private BigDecimal amount;
    private LocalDateTime date;

    public TransferDataEntity() {
    }

    public TransferDataEntity(
            final UUID id,
            final UUID fromWalletId,
            final UUID toWalletId,
            final UUID fromBalanceHistoryId,
            final UUID toBalanceHistoryId,
            final UUID transactionId,
            final BigDecimal amount,
            final LocalDateTime date
    ) {
        this.id = id;
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.fromBalanceHistoryId = fromBalanceHistoryId;
        this.toBalanceHistoryId = toBalanceHistoryId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
    }

    public static TransferDataEntity of(final TransferData transferData) {
        return new TransferDataEntity(
                UUID.fromString(transferData.transferDataId().value()),
                UUID.fromString(transferData.fromWalletId().value()),
                UUID.fromString(transferData.toWalletId().value()),
                UUID.fromString(transferData.fromBalanceHistoryId().value()),
                UUID.fromString(transferData.toBalanceHistoryId().value()),
                UUID.fromString(transferData.transferDataId().value()),
                transferData.amount().value(),
                transferData.date()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransferDataEntity that = (TransferDataEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
