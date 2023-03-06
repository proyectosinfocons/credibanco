package com.hm.credibanco.entity;

import com.hm.credibanco.utils.HashUtil;
import lombok.*;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Data
@Entity
@Builder
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private String pan;

    @Column(name = "validation_number")
    private String validationNumber;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String status;

    public void buildIdentifier() throws NoSuchAlgorithmException {
        if (identifier == null) {
            this.identifier = HashUtil.createSHAHash(pan);
        }
    }

    public void buildValidationNumber() {
        if (validationNumber == null) {
            this.validationNumber = String.valueOf(new Random().ints(0, 99)
                    .findFirst()
                    .getAsInt());
        }
    }

}
