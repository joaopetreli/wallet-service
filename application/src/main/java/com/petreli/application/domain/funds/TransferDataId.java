package com.petreli.application.domain.funds;

import com.petreli.application.exception.ValidationException;
import java.util.UUID;

public record TransferDataId(String value) {

    public TransferDataId {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Invalid value for TransferDataId");
        }
    }

    public static TransferDataId unique() {
        return new TransferDataId(UUID.randomUUID().toString());
    }

    public static TransferDataId with(final String value) {
        try {
            return new TransferDataId(UUID.fromString(value).toString());
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Invalid value for TransferDataId");
        }
    }
}
