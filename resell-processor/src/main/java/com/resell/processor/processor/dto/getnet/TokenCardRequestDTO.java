package com.resell.processor.processor.dto.getnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenCardRequestDTO implements Serializable {

    @JsonProperty("card_number")
    private String cardNumber;

    @JsonProperty("customer_id")
    private String customerId;

}
