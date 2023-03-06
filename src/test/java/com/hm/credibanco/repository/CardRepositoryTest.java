package com.hm.credibanco.repository;

import com.hm.credibanco.config.DefaultJpaTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = DefaultJpaTestConfiguration.class)
class CardRepositoryTest {

    @Autowired CardRepository repository;

    @Test
    @Sql(scripts = "/insert_cards.sql")
    void findByIdentifier() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";

        // when
        var result = repository.findByIdentifier(identifier);

        // then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(identifier, result.get().getIdentifier());
    }

    @Test
    @Sql(scripts = "/insert_cards.sql")
    void findByIdentifierAndValidationNumber() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        var validationNumber = "01";

        // when
        var result = repository.findByIdentifierAndValidationNumber(identifier, validationNumber);

        // then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(identifier, result.get().getIdentifier());
    }
}