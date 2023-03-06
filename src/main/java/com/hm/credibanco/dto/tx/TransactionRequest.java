package com.hm.credibanco.dto.tx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{64}", message = "no cumple con el formato")
    private String identifier;
    @NotNull
    @Pattern(regexp = "[0-9]{6}", message = "no cumple con el formato")
    private String referenceNumber;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal amount;
    @NotNull
    @Size(min = 10, max = 100)
    private String address;

}
