package com.hm.credibanco.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "card_history")
public class CardHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_card")
    private Long idCard;

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

}
