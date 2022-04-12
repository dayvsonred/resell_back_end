package com.resell.processor.processor.dto.getnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenCardDTO implements Serializable {

    @JsonProperty("number_token")
    private String numberToken;


}
