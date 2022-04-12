package com.resell.processor.processor.dto.getnet;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubMerchantDTO implements Serializable {

    private String identification_code;
    private String document_type;
    private String document_number;
    private String address;
    private String city;
    private String state;
    private String postal_code;

}
