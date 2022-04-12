package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO implements Serializable {

    private String number_token;
    private String cardholder_name;
    //private Long security_code;
    //private String brand;
    private String expiration_month;
    private String expiration_year;
}
