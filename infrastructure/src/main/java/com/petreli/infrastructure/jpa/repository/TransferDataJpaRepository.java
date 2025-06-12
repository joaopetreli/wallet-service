package com.petreli.infrastructure.jpa.repository;

import com.petreli.infrastructure.jpa.entities.TransferDataEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TransferDataJpaRepository extends CrudRepository<TransferDataEntity, UUID> {

}
