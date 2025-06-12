package com.petreli.infrastructure.dtos;

import java.math.BigDecimal;

public record TransferFundsDTO(
      Long fromAccountNumber,
      Long toAccountNumber,
      BigDecimal amount
) {

}
