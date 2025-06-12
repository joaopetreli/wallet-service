package com.petreli.infrastructure.dtos;

import java.math.BigDecimal;

public record WithdrawFundsDTO(Long accountNumber, BigDecimal amount) {

}
