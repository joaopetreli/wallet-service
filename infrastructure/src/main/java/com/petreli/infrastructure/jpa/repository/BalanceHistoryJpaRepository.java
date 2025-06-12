package com.petreli.infrastructure.jpa.repository;

import com.petreli.infrastructure.jpa.entities.BalanceHistoryEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface BalanceHistoryJpaRepository extends CrudRepository<BalanceHistoryEntity, UUID> {

    List<BalanceHistoryEntity> findByAccountNumberAndCreatedAtBetween(
          Long accountNumber,
          LocalDateTime start,
          LocalDateTime end
    );
}
