package com.petreli.application.domain.wallet;

import com.petreli.application.exception.ValidationException;
import java.util.UUID;

public record WalletId(String value) {

    public WalletId {
        if (value == null) {
            throw new ValidationException("Invalid value for WalletId");
        }
    }

    public static WalletId unique() {
        return new WalletId(UUID.randomUUID().toString());
    }

    public static WalletId with(final String value) {
        try {
            return new WalletId(UUID.fromString(value).toString());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid value for WalletId");
        }
    }
}
