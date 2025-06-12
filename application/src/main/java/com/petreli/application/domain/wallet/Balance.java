package com.petreli.application.domain.wallet;

import com.petreli.application.exception.ValidationException;
import java.math.BigDecimal;

public record Balance(BigDecimal value) {

    public Balance {
        if (value == null) {
            throw new ValidationException("Invalid value for Balance");
        }
    }

    public Balance add(BigDecimal amount) {
        if (amount == null) {
            throw new ValidationException("Amount to add cannot be null");
        }
        return new Balance(this.value.add(amount));
    }

    public Balance subtract(BigDecimal amount) {
        if (amount == null) {
            throw new ValidationException("Amount to subtract cannot be null");
        }
        return new Balance(this.value.subtract(amount));
    }
}
