package com.resell.processor.processor.dto.getnet;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    private AddressDTO street;
    private AddressDTO number;
    private AddressDTO complement;
    private AddressDTO district;
    private AddressDTO city;
    private AddressDTO state;
    private AddressDTO country;
    private AddressDTO postal_code;
}
