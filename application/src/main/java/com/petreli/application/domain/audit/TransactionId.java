package com.petreli.application.domain.audit;

import com.petreli.application.exception.ValidationException;
import java.util.UUID;

public record TransactionId(String value) {

    public TransactionId {
        if (value == null) {
            throw new ValidationException("Invalid value for TransactionId");
        }
    }

    public static TransactionId unique() {
        return new TransactionId(UUID.randomUUID().toString());
    }

    public static TransactionId with(final String value) {
        try {
            return new TransactionId(UUID.fromString(value).toString());
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Invalid value for TransactionId");
        }
    }
}
