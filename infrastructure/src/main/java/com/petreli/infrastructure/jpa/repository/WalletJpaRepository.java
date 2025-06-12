package com.petreli.infrastructure.jpa.repository;

import com.petreli.infrastructure.jpa.entities.WalletEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletJpaRepository extends CrudRepository<WalletEntity, UUID> {

    Optional<WalletEntity> findByAccountNumber(Long accountNumber);
}
