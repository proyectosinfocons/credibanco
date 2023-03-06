package com.hm.credibanco.repository;

import com.hm.credibanco.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Optional<TransactionEntity> findByIdentifierAndReferenceNumber(String identifier, String referenceNumber);

}
