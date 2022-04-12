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
public class ShippingsDTO implements Serializable {

    private String first_name;
    private String name;
    private String email;
    private String phone_number;
    private String shipping_amount;
    private AddressDTO address;


}
