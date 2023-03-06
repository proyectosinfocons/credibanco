package com.hm.credibanco.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryCardResponse {

    private String pan;
    private String cardHolder;
    private String documentNumber;
    private String phoneNumber;
    private String status;

}
