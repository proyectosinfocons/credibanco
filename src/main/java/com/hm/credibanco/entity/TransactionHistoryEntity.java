package com.hm.credibanco.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "transaction_history")
public class TransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_transaction")
    private Long idTransaction;

    private String identifier;

    @Column(name = "reference_number")
    private String referenceNumber;

    private BigDecimal amount;

    private String address;

    private String status;

}
