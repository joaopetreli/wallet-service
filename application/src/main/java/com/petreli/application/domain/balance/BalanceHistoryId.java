package com.petreli.application.domain.balance;

import com.petreli.application.exception.ValidationException;
import java.util.UUID;

public record BalanceHistoryId(String value) {

    public BalanceHistoryId {
        if (value == null) {
            throw new ValidationException("Invalid value for BalanceHistoryId");
        }
    }

    public static BalanceHistoryId unique() {
        return new BalanceHistoryId(UUID.randomUUID().toString());
    }

    public static BalanceHistoryId with(final String value) {
        try {
            return new BalanceHistoryId(UUID.fromString(value).toString());
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Invalid value for BalanceHistoryId");
        }
    }

}
