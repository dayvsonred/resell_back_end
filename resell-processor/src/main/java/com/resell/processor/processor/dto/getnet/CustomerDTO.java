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
public class CustomerDTO implements Serializable {

    private String customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String document_type;
    private String document_number;
    private String phone_number;
    private BillingAddressDTO billing_address;
}
