package com.hm.credibanco.repository;

import com.hm.credibanco.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByIdentifier(String identifier);

    Optional<CardEntity> findByIdentifierAndValidationNumber(String identifier, String validationNumber);

    Optional<CardEntity> findByPanAndDocumentNumber(String pan, String documentNumber);

}
