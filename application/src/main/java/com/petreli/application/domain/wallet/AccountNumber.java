package com.petreli.application.domain.wallet;

import com.petreli.application.exception.ValidationException;

public record AccountNumber(Long value) {

    public AccountNumber {
        if (value == null || value <= 0) {
            throw new ValidationException("Invalid value for AccountNumber");
        }
    }
}
