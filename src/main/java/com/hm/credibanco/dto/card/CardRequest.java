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
public class CardRequest {

    @NotNull
    @Pattern(regexp = "[0-9]{16,19}", message = "no cumple con el formato, solo números y de 16 a 19 dígitos")
    private String pan;
    @NotNull
    @Pattern(regexp = "[a-zA-Z\\s]{5,50}", message = "solo permite letras y espacios en blanco, de 5 a 50 caracteres")
    private String cardHolder;
    @NotNull
    @Pattern(regexp = "[0-9]{10,15}", message = "no cumple con el formato, solo números y de 10 a 15 dígitos")
    private String documentNumber;
    @NotNull
    @Pattern(regexp = "CREDITO|DEBITO", message = "solo se permite como valor CREDITO o DEBITO")
    private String cardType;
    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "no cumple con el formato, solo números y 10 dígitos")
    private String phoneNumber;

}
