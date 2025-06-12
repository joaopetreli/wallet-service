package com.petreli.infrastructure.dtos;

import java.math.BigDecimal;

public record DepositFundsDTO(Long accountNumber, BigDecimal amount) {

}
