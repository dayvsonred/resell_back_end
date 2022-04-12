package com.resell.processor.processor.dto.getnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsRequestDTO implements Serializable {

    @JsonProperty("seller_id")
    private String sellerId;
}
