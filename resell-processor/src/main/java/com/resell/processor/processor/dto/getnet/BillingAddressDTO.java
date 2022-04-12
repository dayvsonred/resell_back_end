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
public class BillingAddressDTO implements Serializable {

    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String country;
    private String postal_code;

}
