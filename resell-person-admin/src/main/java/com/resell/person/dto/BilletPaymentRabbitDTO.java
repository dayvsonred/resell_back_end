package com.resell.person.dto;


import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BilletPaymentRabbitDTO implements Serializable {

    private String hash;
    private BigInteger amount;
    private String barCode;
    private String status;
    private String token;
}
