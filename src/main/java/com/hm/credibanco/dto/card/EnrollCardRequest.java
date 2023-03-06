package com.hm.credibanco.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollCardRequest {

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{64}", message = "no cumple con el formato")
    private String identifier;
    @NotNull
    @Pattern(regexp = "[0-9]{2}", message = "no cumple con el formato, solo números del 0 al 99")
    private String validationNumber;

}
